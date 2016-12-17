[![](https://jitpack.io/v/guidovezzoni/MVP.svg)](https://jitpack.io/#guidovezzoni/MVP)

# MVP
Simple library to help implementing a base MVP project

Looking online for MVP samples or libraries I could find either very simple projects that didn't account for all 
requirements of the MVP pattern or very complex templates based on dependency injection and reactive libraries.

The idea behind this simple library is to provide a basic structure to start with MVP without involving Dagger or RxJava. It's a very basic and work-in-progress implementation: I will probably fine tune it or add additional features while using it.

Criteria used during the design were:

* use of interfaces for each module (View, Presenter, and Model), they are all grouped in a Contract interface
* presenter is unaware of the view lifecycle, so most likely it should disappear with - say - a configuration change
* Context should be confined in the view, but in some situation it's needed in the presenter or even in the model, so as a compromise their interfaces allow to set and retrieve the view context - checking that it's safe to use it will be left to the implementation
* WeakReference to handle the View instance from the Presenter

# Usage
Add the JitPack repository in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```
dependencies {
	        compile 'com.github.guidovezzoni:mvp:0.1.1'
	}
```

# History

######version 0.1.7 17/12/2016
* gradle fixed (for real)

######version 0.1.6 17/12/2016 [BROKEN]
* gradle fixed

######version 0.1.5 17/12/2016 [BROKEN]
* updated for mvp 0.1.5
* removed some comments
* minor changes for coding style

######version 0.1.4 12/12/2016

* updated ExtendedContract View and OnModelListener to support cancelled network request

######version 0.1.3 11/12/2016

* updated ExtendedContract View to support cancelled network request

######version 0.1.2 11/12/2016

* aar now contains sources
* context calls are now flagged as deprecated
* ExtendedContract improved
* minor changes in BasePresenterImplementation

######version 0.1.1

######version 0.1.0

# Sample App

The sample app shows a simple application for the library.

# License
```
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
```
