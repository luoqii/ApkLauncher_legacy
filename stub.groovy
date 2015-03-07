
createTarget=true;


def createBaseActivity(String packagedir, String packageName, String className) {
	String superClassName=className.split("_")[1]
  	File dir = new File(packagedir);
 	dir.mkdirs()
	
 	String path = packageName.replaceAll("\\.", '/');
	println "path: " + path
	dir = new File(dir.getPath() + "/" + path);
	dir.mkdirs()

	File file = new File(dir, className + ".java")
	file.write """
package ${packageName};

import android.app.Activity;
import android.app.ListActivity;
import android.preference.PreferenceActivity;

public class ${className} extends ${superClassName} {
	public Activity getHostActivity() {
		return this;
	}

}
"""        

}

def createTargetActivity(String packagedir, String packageName, String className) {
	String superClassName="Target_" + className.split("_")[1]
  	File dir = new File(packagedir);
 	dir.mkdirs()
	
 	String path = packageName.replaceAll("\\.", '/');
	println "path: " + path
	dir = new File(dir.getPath() + "/" + path);
	dir.mkdirs()

	File file = new File(dir, className + ".java")
	file.write """
package ${packageName};


public class ${className} extends org.bbs.apklauncher.emb.auto_gen.${superClassName} {

}
"""        

}


if (createTarget == true) {
	println "createTarget"
	createTargetActivity "./src", 'com.example.android.apis.stub', 'Base_Activity'
	createTargetActivity "./src", 'com.example.android.apis.stub', 'Base_ListActivity'
	createTargetActivity "./src", 'com.example.android.apis.stub', 'Base_PreferenceActivity'
} else {
	println 'createBaseActivity'
	createBaseActivity "./src", 'com.example.android.apis.stub', 'Base_Activity'
	createBaseActivity "./src", 'com.example.android.apis.stub', 'Base_ListActivity'
	createBaseActivity "./src", 'com.example.android.apis.stub', 'Base_PreferenceActivity'
}


println args[0]
