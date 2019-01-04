package reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ExtractContentFromPDF {
	public static void main(String[] args) throws IOException {

		try (PDDocument document = PDDocument.load(new File("/home/agile/Downloads/dev_yaganti-converted.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				// System.out.println("Text:" + st);

				// split by whitespace
				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
//					System.out.println(line);
					findEmail(line);
					System.out.println(findPhoneNumber(line));
					System.out.println(findEducations(line));
					System.out.println(findWorkExperiences(line));
				System.out.println(findApplicantSkills(line));
				}

			}
		}
	}

	private static void findEmail(String details) {
		List<String> emailList = new ArrayList<>();
		Pattern pattern = Pattern.compile(RegEx.EMAIL.toString(), Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(details);
		while (matcher.find()) {
			emailList.add(matcher.group());
		}
		if (!emailList.isEmpty())
			System.out.println(emailList.toString());	
		}

	/**
	 * Find phone numbers in the resume
	 * 
	 * @param line to search for
	 * @return phone numbers found from resume
	 */
	private static String findPhoneNumber(String line) {
		List<String> phoneNumbers = new ArrayList<>();
		Pattern pattern = Pattern.compile(RegEx.PHONE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			phoneNumbers.add(matcher.group());
		}
		if (!phoneNumbers.isEmpty())
			return phoneNumbers.toString();
		else
			return "";
	}

	private static String findEducations(String line) {
		ParserHelper parser = new ParserHelper();
		// just like findWorkExperiences
		int indexOfEducation = parser.getIndexOfThisSection(RegEx.EDUCATION, line);
		if (indexOfEducation != -1) {
			int nextSectionIndex = 0;
			List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
			String educationsText = line.replaceFirst(RegEx.EDUCATION.toString(), "");
			for (int index = 0; index < listOfSectionIndexes.size(); index++) {
				if (listOfSectionIndexes.get(index) == indexOfEducation) {
					// if education is the last section, then there is no
					// nextSectionIndex
					if (index == listOfSectionIndexes.size() - 1) {
						return educationsText.substring(indexOfEducation);
					} else {
						// index + 1: where index is the index of education section heading, +1
						// the index of the next section heading
						nextSectionIndex = listOfSectionIndexes.get(index + 1);
						break;
					}
				}
			}
			return educationsText.substring(indexOfEducation, nextSectionIndex);
		}
		return "";
	}

	private static String findWorkExperiences(String line) {
		ParserHelper parser = new ParserHelper();
		/*
		 * Algorithm: copy texts starting from experience section index to the following
		 * section index experience index is LESS THAN the following section index,
		 * therefore
		 * 
		 * Example: section indexes [24, 355, 534, 669] index of experience section =
		 * 355 therefore, the following section index would be 534 we can get the texts
		 * that encompasses experience section by substring => (indexOfExperience,
		 * beginIndexOfFollowingSection)
		 * 
		 */
		int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
		if (indexOfExperience != -1) {
			int nextSectionIndex = 0; // index that follows experience section
			String experiencesText = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
			for (int index = 0; index < parser.getAllSectionIndexes(line).size(); index++) {
				if (parser.getAllSectionIndexes(line).get(index) == indexOfExperience) {
					// experience section is not always in the middle
					// rarely they may appear as the last section
					if (index == parser.getAllSectionIndexes(line).size() - 1) {
						return experiencesText.substring(indexOfExperience);
					} else {
						nextSectionIndex = parser.getAllSectionIndexes(line).get(index + 1);
						break;
					}
				}
			}
			return experiencesText.substring(indexOfExperience, nextSectionIndex);
		}
		return "";
	}

	/*private static String findApplicantSkills(String line) {
		ParserHelper parser = new ParserHelper();
		List<String> phoneNumbers = new ArrayList<>();
		Pattern pattern = Pattern.compile(RegEx.PHONE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			phoneNumbers.add(matcher.group());
		}
		return phoneNumbers.toString();
	}*/

	private static String findApplicantSkills(String line) {
		ParserHelper parser = new ParserHelper();
		int indexOfSkillsSection = parser.getIndexOfThisSection(RegEx.SKILLS, line);

		if (indexOfSkillsSection != -1) {
			List<Integer> sectionIndexes = parser.getAllSectionIndexes(line);
			String skillsText = line.replaceFirst(RegEx.SKILLS.toString(), "");
			int nextSectionIndex = 0;
			for (int index = 0; index < sectionIndexes.size(); index++) {
				if (sectionIndexes.get(index) == indexOfSkillsSection) {
					if (index == sectionIndexes.size() - 1) {
						return skillsText.substring(indexOfSkillsSection);
					} else {
						nextSectionIndex = sectionIndexes.get(index + 1);
						break;
					}
				}
			}
			return skillsText.substring(indexOfSkillsSection, nextSectionIndex);
		}
		return "";
	}

}