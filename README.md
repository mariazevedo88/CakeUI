CakeUI
======

Framework to build Android applications easily.

by 

Mariana Azevedo (mariana@bsi.ufla.br)

Sarah Caixeta (caixeta.sarah@gmail.com)

# About CakeUI
This is a framework created to bootstrap Android development.
We have included on the framework's architecture, generic implementations 
of the most Android App Components to help developers of all levels 
(beginners and advanced) to create Android applications easily and quickly.
This framework also uses, as dependency library, the ActionBarSherlock library 
to customize the ActionBar component.

#Structure

This framework implements basics structures to build an Android application. Some of
these structures are:

* <b>CakeActivity</b>: an generic Activity that supports user's interaction, providing an 
ActionBar on the top on the application, a communication with a generic Service 
and BroadcastReceiver, and maps the fragments sequence used to build an UI flow.

* <b>CakeFragment</b>: an generic Fragment that represents a behavior or a portion of user 
interface in an Activity. The CakeFragment provides way to manipulate multiple 
fragments and indicates what of them is the main fragment of the UI flow.

* <b>CakeDialog</b>: an generic Dialog that supports build some dialogs types as success, 
error, information and confirmation.

* <b>CakeExpandableListViewAdapter, CakeListViewAdapter, CakeGridViewAdapter</b>: generic adapters that provides implementations to manipulate data in an ExpandableListView, ListView and GridView, respectively.

* <b>CakeService and CakePeriodicService</b>: generic services to perform a longer-running operation while not interacting with the user or to supply functionality for other 
applications to use. The last one provides a way to execute a service periodically.

Others like CakeBroadcastReceiver, CakeAsyncTask, CakeApplication and some data 
structures are supported too. We recommend the beginners to read and follow the 
[Android Developers reference site] [1].  

#Dependency

After downloading the CakeUI and [ActionBarSherlock] [2], import the ActionBarSherlock project into your workspace. With the right mouse button, go to <b>Properties</b> -> <b>Android</b> and on the box <b>Library</b>, click in <b>Add</b> button to add ActionBarSherlock project into CakeUI. It is important that, both projects have the checkbox "Is Library" marked).   

#How to use

CakeUI is used as a Android library project, that needs to be imported by your application.
After created your project and import the CakeUI project into your workspace, with the right 
mouse button, go to <b>Properties</b> -> <b>Android</b> and on the box <b>Library</b>, click in <b>Add</b> button to add CakeUI into your application.

[1]: http://developer.android.com/reference/packages.html "Android Developers reference site"

[2]: https://github.com/JakeWharton/ActionBarSherlock/releases/tag/4.4.0 "ActionBarSherlock"

