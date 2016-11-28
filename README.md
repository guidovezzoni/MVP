# MVP
Simple library to help implementing a base MVP project

Looking online for MVP samples or libraries I could find either very simple projects that didn't account for all 
requirements of the MVP pattern or very complex templates based on dependency injection and reactive libraries.

The idea behind this simple library is to provide a basic structure to start with MVP without involving Dagger or RxJava.

Criteria used during the design were:

* use of interfaces for each module (View, Presenter, and Model), they are all grouped in a Contract interface
