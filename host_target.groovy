

hostFile = new File("Host_Activity.java.template")
targetFile = new File("Target_Activity.java.template")
stubFile = new File("Stub_Activity.java.template")

HOST_NOTE='//do NOT edit this file, auto-generated by createTemplage.groovy from ' + hostFile.name
TARGET_NOTE='//do NOT edit this file, auto-generated by createTemplage.groovy from ' + targetFile.name
LOOP = 77
ignoreTags = new ArrayList<String>();
tags = new ArrayList<String>();

def genActivityFile(File templateFile, File file, String superClassName, String note) {
    i = 0
    file.mkdir()
    file.delete()
    REPLACE='SUPER_CLASS'

    println 'crated file: ' + file
    println 'superClassName: ' + superClassName 

 	ignoreTags = new ArrayList<String>();
	tags = new ArrayList<String>();
       	if ("Activity".equals(superClassName)) {
 			ignoreTags.add "ListActivity"
 			ignoreTags.add "FragmentActivity"
        	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
       	} else if ("ListActivity".equals(superClassName)) {
			ignoreTags.add "FragmentActivity"
           	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
        } else if ("FragmentActivity".equals(superClassName)) {
   			ignoreTags.add "ListActivity"
 			ignoreTags.add "No_FragmentActivity"
			ignoreTags.add "ActivityGroup"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
		} else if ("ActivityGroup".equals(superClassName)) {
 			ignoreTags.add "ListActivity"
 			ignoreTags.add "FragmentActivity"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
        } else if ("PreferenceActivity".equals(superClassName)) {
 			ignoreTags.add "FragmentActivity"
        	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
        } else if ("ExpandableListActivity".equals(superClassName)) {
 			ignoreTags.add "FragmentActivity"
        	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "ListActivity"
        	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "TabActivity"
        	ignoreTags.add "ActionBarActivity"
        } else if ("TabActivity".equals(superClassName)) {
 			ignoreTags.add "FragmentActivity"
        	ignoreTags.add "ActivityGroup"
        	ignoreTags.add "ListActivity"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "ActionBarActivity"
        } else if ("ActionBarActivity".equals(superClassName)) {
   			ignoreTags.add "ListActivity"
 			ignoreTags.add "No_ActionBarActivity"
			ignoreTags.add "ActivityGroup"
        	ignoreTags.add "PreferenceActivity"
        	ignoreTags.add "ExpandableListActivity"
        	ignoreTags.add "TabActivity"
		}

    templateFile.eachLine {
        updateTag(it)
        if (shouldIgnore(it)) {
        	
        }

 	else {
        if (it.contains(REPLACE)) {
            it = it.replaceAll(REPLACE, superClassName)
        } 
        
           it = it.replaceAll(/mHostActivity.isValidFragment\(/, 'ActivityReflectUtil.isValidFragment(mHostActivity, ');

        file.append(it)
        file.append("\n")

        i++
        if (i % LOOP  == 0) {
           file.append(note)
           file.append "\n"
        }
 	}
   }
}

def updateTag(String it) {
	if (it.contains("tag_start:")) {
		it = it.substring(it.indexOf(":") + 1)
  		String[] tag = it.split(" ")
                for (String t : tag) {
			tags.add(t)
   		}
 		println 'tag_start:' + tag
	 	println tags
	}
	
	if (it.contains("tag_end:")) {
		it = it.substring(it.indexOf(":") + 1)
		String[] tag = it.split(" ")
                for (String t : tag ) {
			tags.remove(t)
		}
		println 'tag_end:' + tag
		println tags
	}
}

def shouldIgnore(String it){
	if (it.contains("tag_start:")) return false;
	
	for (String i : ignoreTags) {
		for (String t : tags ) {
 			if (i.equals(t)){
				return true
			}
		}
	}
//	if (it.contains('tag_end:')) return true
     
        return false;
}

def genHostTargetActivityFile(String dir, String superClassName) {
    genActivityFile(hostFile, new File(dir, "Host_" + superClassName + ".java"), superClassName, HOST_NOTE) 
    genActivityFile(targetFile, new File(dir, "Target_" + superClassName + ".java"), superClassName, TARGET_NOTE) 
    genActivityFile(stubFile, new File(dir, "Stub_" + superClassName + ".java"), superClassName, TARGET_NOTE) 
}


genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'Activity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'ListActivity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'TabActivity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'ActivityGroup'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'FragmentActivity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'ActionBarActivity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'PreferenceActivity'
genHostTargetActivityFile "src/org/bbs/apklauncher/emb/auto_gen", 'ExpandableListActivity'
