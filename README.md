### Describe your solution here
My solution for the assessment is creating a modular app, following the SOLID principles and
a Clean Code pattern focused on features, adding some of the newest solutions of native
Android Development.

Its divided in three modules, presentation, domain and data. And each layer is supposed to
do only what it is required from them.

- For the presentation I've used compose with flowState, and added animations for the icons
of the weather with Lottie.
- For Domain, I have tried to make it as independent as possible from Android and from the
other layers.
- For Data, I've divided everything into repositories, and their own dataSources. Using Retrofit
and Moshi for the calls to the API and the parse of its response. Also with aid from AI (chatGPT)
I have created a coordinateDataSource to get random coordinates from parts around the world 
that contain land.

All this aside, I have used Hilt to inject al dependencies and added tests to cover some of 
domain and data.

There are many things that could be expanded like tests for the views and viewModel. A extraction
of the literal strings that are related to the views. I would also like to add an error management
system for the online petitions. As well as a refinement of the main view of the fragment.

But given the time, I'm happy with the results. I'll be adding the apk by mail just in case
here are any issues loading the project.

Regards
Rubén

