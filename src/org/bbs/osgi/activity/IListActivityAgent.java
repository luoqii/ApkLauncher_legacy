package org.bbs.osgi.activity;

import android.content.Intent;
import android.widget.AbsListView;
import android.widget.SimpleAdapter;

public interface IListActivityAgent extends IActivityAgent {

	
	// ListActivity
	public abstract AbsListView getListView();
	public abstract void setListAdapter(SimpleAdapter simpleAdapter);
	// ListActivity

}