package reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;

public class ParserHelper {
    //Logger logger = (Logger) LoggerFactory.getLogger(ParserHelper.class);

    /**
     * Fetch the index of a single section heading.
     * 
     * @param regEx
     * @param line
     * @return index of given regEx (section)
     */
    public int getIndexOfThisSection(RegEx regEx, String line) {
	RegEx[] sectionRegex = { RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE, RegEx.SKILLS, RegEx.LANGUAGE,
		RegEx.INTEREST, RegEx.MEMBERSHIP, RegEx.ADDITIONAL };
	List<Integer> indexOfThisSection = new ArrayList<>();
	for (RegEx r : sectionRegex) {
	    if (r.equals(regEx)) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL | Pattern.UNIX_LINES);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
		    indexOfThisSection.add(matcher.start());
		}
	    }
	}
	if (!indexOfThisSection.isEmpty()) {
	    return indexOfThisSection.get(0);
	}
	return -1;
    }

    /**
     * Gel indexes of all sections from resume.
     * 
     * @param line
     * @return index of each section
     */
    public List<Integer> getAllSectionIndexes(String line) {
	RegEx[] sectionRegex = { RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE, RegEx.SKILLS, RegEx.LANGUAGE,
		RegEx.INTEREST, RegEx.MEMBERSHIP, RegEx.ADDITIONAL };
	List<Integer> indexesOfSection = new ArrayList<>();
	for (RegEx r : sectionRegex) {
	    Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL );
	    Matcher matcher = pattern.matcher(line);
	    while (matcher.find()) {
		indexesOfSection.add(matcher.start());
	    }
	}
	Collections.sort(indexesOfSection);
	return indexesOfSection;
    }

    /**
     * @param regEx
     *            get section indexes but not regEx, the regEx to be excluded
     * @param line
     *            the string to parse for
     * @return indexes that follows regEx section
     */
    public List<Integer> getSectionIndexesExcludeOne(RegEx regEx, String line) {
	RegEx[] sectionRegex = { RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE, RegEx.SKILLS, RegEx.LANGUAGE,
		RegEx.INTEREST, RegEx.MEMBERSHIP, RegEx.ADDITIONAL };
	List<Integer> indexesOfSection = new ArrayList<>();
	for (RegEx r : sectionRegex) {
	    if (!r.equals(regEx)) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL )  ;
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
		    indexesOfSection.add(matcher.start());
		}
	    }
	}
	Collections.sort(indexesOfSection);
	return indexesOfSection;
    }

}
