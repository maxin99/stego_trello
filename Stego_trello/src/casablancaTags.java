
public class casablancaTags {

	public casablancaTags() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String BAU_TEXT = "BAU";
	public static final String BLOCKED_TEXT = "Blocked";
	public static final String REVIEW_TEXT = "Review";
	public static final String PENDING_TEXT = "Pending";
	public static final String ONTIME_TEXT = "ONTIME";
	
	public static final String BAU_COLOR = "rgba(252, 230, 198, 1)";
	public static final String BAU_COLOR_2 = "rgba(253, 244, 231, 1)";
	public static final String BAU_COLOR_3 = "rgba(255, 226, 189, 1)";
	
	public static final String BLOCKED_COLOR = "rgba(237, 219, 244, 1)";
	public static final String BLOCKED_COLOR_2 = "rgba(247, 240, 250, 1)";
	
	public static final String REVIEW_COLOR = "rgba(188, 217, 234, 1)";
	public static final String REVIEW_COLOR_2 = "rgba(188, 217, 234, 1)";
	
	public static final String PENDING_COLOR = "rgba(189, 236, 243, 1)";
	public static final String PENDING_COLOR_2 = "rgba(228, 247, 250, 1)";
	
	public static final String ONTIME_COLOR = "rgba(211, 246, 228, 1)";
	public static final String ONTIME_COLOR_2 = "rgba(236, 251, 243, 1)";
	public static final String ONTIME_COLOR_3 = "rgba(211, 241, 167, 1)";
	
	public static String returnTag(String text, String color) {
		
		if(text.equals(BAU_TEXT) && (color.equals(BAU_COLOR) || color.equals(BAU_COLOR_2) || color.equals(BAU_COLOR_3)) ) {
			return "BAU";
		} else if(text.equals(BLOCKED_TEXT) && (color.equals(BLOCKED_COLOR) || color.equals(BLOCKED_COLOR_2))) {
			return "BLOCKED";
		} else if(text.equals(REVIEW_TEXT) && (color.equals(REVIEW_COLOR) || color.equals(REVIEW_COLOR_2))) {
			return "REVIEW";
		} else if(text.equals(PENDING_TEXT) && (color.equals(PENDING_COLOR) || color.equals(PENDING_COLOR_2))) {
			return "PENDING";
		} else if(text.equals(ONTIME_TEXT) && (color.equals(ONTIME_COLOR) || color.equals(ONTIME_COLOR_2) || color.equals(ONTIME_COLOR_3))) {
			return "ONTIME";			
		}
		
		return "";
	}

}
