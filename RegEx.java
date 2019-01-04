package reader;

public enum RegEx {
    // How RegEx works
    // These regular expressions must be strictly imposed, this is due to one particular reason:
    // For instance, an experience section might contain multiple instances of the word experience. 
    // Therefore, a strict regular expression for section headings must apply.
    // For experience, the solution was to get the index of the word experience 
    // which matches exactly to the following
    // Experience|Experiences|EXPERIENCE|EXPERIENCES. This excluded
    // experience or experiences to be valid. The same notion applies to other section headings.
    LINK ("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"), 
    EMAIL ("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"), 
    PHONE  ("\\(?([0-9]{3})\\)?([0-9]{3})?([0-9]{4})"), 
    OBJECTIVE ("\\b(Objective(s?)|OBJECTIVE(S?)|Summary|SUMMARY)([^-!@#$%^&*()+.,?])\\b"), // summary included here
    EDUCATION ("\\b(Education(s?)|EDUCATION(S?)|Bachelor of Technology(s?)|BTECH(S?)|BTech(s?)|SSC(S?)|ssc(S?)|inter(S?)|intermediate(S?))\\b"),
    EXPERIENCE ("\\b(Experience(s?)|EXPERIENCE(S?))|java(?)|JAVA(?)|JavaTechnologies(?) \\b"),
    SKILLS ("\b (skill(s?) & Expertise(s?)|Tool(s?) & Technolog(y?|ies?)|java(S?)|Skill(s?)|java(?)|Spring(?)) \b"),
    LANGUAGE ("\\b(Language(s?)|LANGUAGE(S?))\\b"),
    INTEREST ("\\b(Interest(s?)|INTEREST(S?)|Activity|Activities|ACTIVITY|ACTIVITIES)\\b"),
    MEMBERSHIP ("\\b(Membership(s?)|MEMBERSHIP(S?))\\b"),
    ADDITIONAL ("\\b(Award(s?)|AWARD(S)|Honor(s?)|HONOR(S?)|Certification(s?)|CERTIFICATION(S?)|Accomplishment(s?)|ACCOMPLISHMENT(S?)|Project(s?)|PROJECT(S?))\\b"),
    DATEFROMTO ("([A-Za-z]+\\s)?([0-9]{4})\\s[-]\\s\\b((P|p)resent|(C|c)urrent)\\b|([A-Za-z]+\\s)?([0-9]{4})");
	// PHONE ("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"),
	//^(?:(?:\+|0{0,2})91(\s*[\ -]\s*)?|[0]?)?[789]\d{9}|(\d[ -]?){10}\d$
	// EDUCATION  (?i)G[a-b](?-i).*

    /**
     * Note:
     * - if you have a combination of words make sure you put them in the beginning of the list
     */
    
    private final String name;

    private RegEx(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return this.name;
    }
}