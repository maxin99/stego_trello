/**
 * A class to define all of the colors of the different projects
 * And process check for them
 * 
 * @author CARMEN
 *
 */
public class ProjectColors {

	public ProjectColors() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String CASABLANCA_1_1 = "rgba(123, 200, 108, 1)";
	public static final String CASABLANCA_1_2 = "rgba(31, 132, 90, 1)";
	public static final String CASABLANCA_1_3 = "rgba(75, 206, 151, 1)";
	
	public static final String CASABLANCA_2_1 = "rgba(91, 164, 207, 1)";
	public static final String CASABLANCA_2_2 = "rgba(87, 157, 255, 1)";
	public static final String CASABLANCA_2_3 = "rgba(204, 224, 255, 1)";
	
	public static final String TOKIO_1_1 = "rgba(245, 221, 41, 1)";
	public static final String TOKIO_1_2 = "rgba(248, 230, 160, 1)";
	public static final String TOKIO_1_3 = "rgba(226, 178, 3, 1)";
	public static final String TOKIO_2_1 = "rgba(41, 204, 229, 1)";
	public static final String TOKIO_2_2 = "rgba(96, 198, 210, 1)";
	public static final String TOKIO_2_3 = "rgba(193, 240, 245, 1)";
	
	public static final String MOON_1_1 = "rgba(255, 175, 63, 1)";
	public static final String MOON_1_2 = "rgba(250, 165, 61, 1)";
	public static final String MOON_1_3 = "rgba(255, 226, 189, 1)";
	public static final String MOON_2_1 = "rgba(109, 236, 169, 1)";
	public static final String MOON_2_2 = "rgba(148, 199, 72, 1)";
	public static final String MOON_2_3 = "rgba(211, 241, 167, 1)";
	
	public static final String SPRING_1_1 = "rgba(239, 117, 100, 1)";
	public static final String SPRING_1_2 = "rgba(248, 116, 98, 1)";
	public static final String SPRING_1_3 = "rgba(255, 210, 204, 1)";
	public static final String SPRING_2_1 = "rgba(255, 142, 212, 1)";
	public static final String SPRING_2_2 = "rgba(231, 116, 187, 1)";
	public static final String SPRING_2_3 = "rgba(253, 208, 236, 1)";
	
	public static final String SEPTEMBER_1_1 = "rgba(205, 141, 229, 1)";
	public static final String SEPTEMBER_1_2 = "rgba(159, 143, 239, 1)";
	public static final String SEPTEMBER_1_3 = "rgba(223, 216, 253, 1)";
	public static final String SEPTEMBER_2_1 = "rgba(23, 43, 77, 1)";
	public static final String SEPTEMBER_2_2 = "rgba(133, 144, 162, 1)";
	public static final String SEPTEMBER_2_3 = "rgba(220, 223, 228, 1)";
	
	public static String getProject(String color) {
		
		if(color.equals(CASABLANCA_1_1) || color.equals(CASABLANCA_1_2) || color.equals(CASABLANCA_1_3) ||
				color.equals(CASABLANCA_2_1) || color.equals(CASABLANCA_2_2) || color.equals(CASABLANCA_2_3)) {
			
			return "CASABLANCA";
			
		} else if(color.equals(TOKIO_1_1) || color.equals(TOKIO_1_2) || color.equals(TOKIO_1_3) ||
				color.equals(TOKIO_2_1) || color.equals(TOKIO_2_2) || color.equals(TOKIO_2_3)) {
			
			return "TOKIO";
			
		} else if(color.equals(MOON_1_1) || color.equals(MOON_1_2) || color.equals(MOON_1_3) ||
				color.equals(MOON_2_1) || color.equals(MOON_2_2) || color.equals(MOON_2_3)) {
			
			return "MOON";
			
		} else if(color.equals(SPRING_1_1) || color.equals(SPRING_1_2) || color.equals(SPRING_1_3) ||
				color.equals(SPRING_2_1) || color.equals(SPRING_2_2) || color.equals(SPRING_2_3)) {
			
			return "SPRING";
			
		} else if(color.equals(SEPTEMBER_1_1) || color.equals(SEPTEMBER_1_2) || color.equals(SEPTEMBER_1_3) ||
				color.equals(SEPTEMBER_2_1) || color.equals(SEPTEMBER_2_2) || color.equals(SEPTEMBER_2_3)) {
			
			return "SEPTEMBER";
			
		}

		return "";
	}
	
}
