package hello.controller;

public class EmailTemplate {
	public String template(String password) {
		String str = "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>\r\n" + 
				"    \r\n" +
				"        <a href='#'>\r\n" + 
				"     <img src='https://res.cloudinary.com/dkz2pm90i/image/upload/v1573069140/Capture_m5of1o.png'\r\n" + 
				"    width=180 height=50/>\r\n" + 
				"        </a>\r\n" + 
				"\r\n" + 
				"        <div style='font-family: Arial; border-color: #bce8f1;'>\r\n" + 
				"\r\n" + 
				"<div style='vertical-align:middle; text-align:center; font-family: Tahoma;'>\r\n" + 
				"<h1>\r\n" + 
				"Your new password is: <br>\r\n" + password +
				"</h1>\r\n" + 
				"    \r\n" + 
				"\r\n" + 
				"    <p style='color:#A9A9A9;'>\r\n" + 
				"        Log in with this new password and you will be prompted to change to a new password later. \r\n" + 
				"    </p>\r\n" + 
				"    \r\n" + 
				"           \r\n" + 
				"\r\n" + 
				"    <a style='font-size:0.8em; color:#A9A9A9; text-decoration:none;' href='#'>\r\n" + 
				"<p>\r\n" + 
				"    Gambit Pte. Ltd.,\r\n" + 
				"    </p>\r\n" + 
				"</a>\r\n" + 
				"    \r\n" + 
				"            <hr/>\r\n" +             
				"\r\n" + 
				"</div>\r\n" + 
				"  </div>\r\n" + 
				"</div>\r\n" + 
				"";
		return str;
	}
	
}
