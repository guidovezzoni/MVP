# MVP
Simple library to help implementing a base MVP project

Looking online for MVP samples or libraries I could find either very simple projects that didn't account for all 
requirements of the MVP pattern or very complex templates based on dependency injection and reactive libraries.

The idea behind this simple library is to provide a basic structure to start with MVP without involving Dagger or RxJava. It's a very basic and work-in-progress implementation: I will probably fine tune it or add additional features while using it.

Criteria used during the design were:

* use of interfaces for each module (View, Presenter, and Model), they are all grouped in a Contract interface
* presenter is unaware of the view lifecycle, so most likely it should disappear with - say - a configuration change
* Context should be confined in the view, but in some situation it's needed in the presenter or even in the model, so as a compromise their interfaces allow to set and retrieve the view context - checking that it's safe to use it will be left to the implementation

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