# Overview of Project 

ChemQAnalytica is a chemistry simulation aimed at helping students visualizing and preparing for chemistry quantitative analysis experiments of inorganic salts. The application includes an experiment space and, a quiz functionality to test the knowledge of students and explanation based on observations of what the students see on experiment space. Documents related to how certain molecules react can also be accessed through the application. 

# User Guide to Program 

Splash screen: 

<img width="424" height="455" alt="Screenshot 2026-05-27 at 10 21 11 AM" src="https://github.com/user-attachments/assets/c9e67fef-64e0-4805-8c97-54a7f7d64466" />

Display of the application signature icon to catch attention 

## Homepage: 

Open up after splash screen has finished running 

Button “New experiment” – open up the experiment space which will be seen later in this document 

Button “New notes” – open up .txt file in the application supported by the desktop to read and write to the file 

Button “Safety precautions” and “QA notes” open up .pdf file loaded previously in the application in the application supported by the desktop 

Previews of the .pdf files can be seen below: 

<img width="1470" height="755" alt="Screenshot 2026-05-27 at 10 43 45 AM" src="https://github.com/user-attachments/assets/f8ccfaa9-5a44-4bcf-a3ec-916796182e89" />

<img width="1470" height="751" alt="Screenshot 2026-05-27 at 10 44 13 AM" src="https://github.com/user-attachments/assets/1e65e3d8-f820-4561-b0ea-2b97ea7e6ec0" />

## Menu bar: 

- Home: 

   - New…: Open up new experiment space – the same functionality as “New experiment” button 

   - Exit: Exit homepage also means closing the application window 

- Edit: 

   - Delete: delete all new experiment spaces and notes by the user 

- Help: 

   - About the programmer: shows details about the programmer

<img width="706" height="507" alt="Screenshot 2026-05-27 at 10 44 51 AM" src="https://github.com/user-attachments/assets/0f8f38bc-87aa-4139-a529-50afd932c416" />

## Experiment space

Experiment space has a tab below as the main control. There are 4 tabs, 3 non-disabled, 1 is disabled until certain actions is taken in 3[rd] tab to unlock it 

### Equipment tab

Provides basic equipment like litmus paper for testing pH, test tubes, heating equipment (lighter and Bunsen burner) and splints. Take note that there is a limit on the number of equipment that can be added to the experiment space in order to prevent clogging of components on the screen: 3 litmus papers, 3 test tubes, 1 set of heating equipment and 3 splints 

Attempts to add more than that will result in warning boxes popping up with the respective equipment 

<img width="714" height="303" alt="Screenshot 2026-05-27 at 10 23 54 AM" src="https://github.com/user-attachments/assets/2c53a9d7-d566-46ca-87df-241ad1d166f8" />

### Substances tab

Allow user to make use of the equipment provided 

Testing chemicals are NaOH, NH3, AgNO3 and BaCl2, which are the common testing chemicals to be seen in QA. Always remember to select the test tube to add to! Otherwise warning box will also be thrown 

<img width="796" height="594" alt="Screenshot 2026-05-27 at 10 24 27 AM" src="https://github.com/user-attachments/assets/e265be00-6dbc-476a-b661-3a492965cdc6" />

Black panel helps user to tell the difference when there is a white or transparent content in the test tube for better differentiation 

### Question tab

This is where the knowledge about the experiment observations is tested. Always remember to select the respective test tube is important. Otherwise the responses by the application will be confusing 

<img width="612" height="160" alt="Screenshot 2026-05-27 at 10 25 09 AM" src="https://github.com/user-attachments/assets/c9f20c38-9532-4711-a54d-86ab21ff02b6" />

<img width="939" height="662" alt="Screenshot 2026-05-27 at 10 25 46 AM" src="https://github.com/user-attachments/assets/a620db7d-cef6-42cb-9330-c18d50b98ad7" />

<img width="954" height="671" alt="Screenshot 2026-05-27 at 10 26 08 AM" src="https://github.com/user-attachments/assets/86919772-6542-431d-a515-98ae605b84fc" />

### Explanation tab

Based on a pre written template, explanation is given in details, questions can also be written and saved to a .txt file as shown below for better references 

<img width="617" height="112" alt="Screenshot 2026-05-27 at 10 27 13 AM" src="https://github.com/user-attachments/assets/813c1cee-37cd-47ad-ad43-7948075a373d" />

<img width="497" height="351" alt="Screenshot 2026-05-27 at 10 27 31 AM" src="https://github.com/user-attachments/assets/7d4e7313-8888-4380-bd2a-a2688faf6b87" />

### Menu bar

Delete in Edit allows user to separately removing components from the experiment space Other than that, other menu items are clear as their text stated 

<img width="554" height="309" alt="Screenshot 2026-05-27 at 10 28 19 AM" src="https://github.com/user-attachments/assets/e15ba093-4eee-44f9-b3cf-a49f2d9f584b" />

# Class Design 

MVC Structure was intensively used throughout the class design for this project. 

Implementation of small MVC structure on each FX Object. Each FX Object has a model class to control its properties and a view class which represents the properties on application window. 

Data is loaded into database and history class to control the substances behavior 

The UML diagram below is generated by Visual Paradigm 

<img width="841" height="465" alt="Screenshot 2026-05-27 at 10 28 54 AM" src="https://github.com/user-attachments/assets/aafb4553-cf6e-4e1f-a42c-715820169669" />

<img width="844" height="313" alt="Screenshot 2026-05-27 at 10 29 49 AM" src="https://github.com/user-attachments/assets/5d95942b-6ea0-423d-89e0-5632fb08c93e" />

<img width="815" height="227" alt="Screenshot 2026-05-27 at 10 30 08 AM" src="https://github.com/user-attachments/assets/06b9ffb6-4e81-4362-8033-7b7dd80a48b3" />


# Explanation of Code 

Threading has been used to show behaviors of components presented in the application as well as regular expression to categorize types of cations and anions based on oxidation number. 

FXObjects are made from smaller shapes in Shape API of Java for better control when there is reaction happening, especially in the test tube body and complicated structures like bunsen burner or lighter. 

A lot of working with shapes and their properties are involved so it requires a deep connection between normal shapes and components that are involved in the experiment. 

For now, the application chemicals are hard coded because of the sophistication of chemistry. More work on this could be done. 

# Testing Methodology and Results 

## 1. Arc Test
Test tubes are the objects receiving most focus in QA experiments. Therefore it is important to test the pleasant look and behavior that reflect the correct phenomena that will happen in real life of the test tube. 

   - The main focus for this test is the bottom part of the test tube. A semicircle represents the solution part and the arc represent the precipitate (if any). It is expected that the surface of the content is flat and transparency of content is independent from the border when animation is taking place to display the reaction happening. 

Note: Color should be accurate and transparency should be able to differentiate precipitate in the solution. Correct output also reflects the smaller MVC model for each object is working well. Input: CuSO4, then add NaOH, then add BaCl2 

<img width="646" height="168" alt="Screenshot 2026-05-27 at 10 31 11 AM" src="https://github.com/user-attachments/assets/c6cb175f-1836-4eaf-a08a-f63755a1be61" />

Input: FeCl3, them add NH3 

<img width="335" height="157" alt="Screenshot 2026-05-27 at 10 31 48 AM" src="https://github.com/user-attachments/assets/8f6ee3e2-86d1-477a-945d-da437ed1cf5b" />

Input: Random – check via console shows the salt is FeI2, then add AgNO3 

<img width="471" height="150" alt="Screenshot 2026-05-27 at 10 32 22 AM" src="https://github.com/user-attachments/assets/2e9082cb-0348-47f6-9647-3e3b00c2385b" />

Output: Animation shows the arc smoothly increasing in opacity to indicate formation of precipitate 

## 2. Ions and Salts Test

Test the text analysis of regular expression and its accuracy 

## 3. FXObjects Test

<img width="172" height="396" alt="Screenshot 2026-05-27 at 10 32 54 AM" src="https://github.com/user-attachments/assets/edb7893d-25d6-4717-bb0f-98395bec99fd" />

To test whether shapes that make up the object turn out as similar as possible to it is in reality. This is to ensure the GUI experience to be pleasant to look at. In this case, all default constructors of FXObject class were called to test the functionality of paint() method in interface Paintable 

## 4. PDF View Test 

## 5. Experiment Space Test
Because the application is based on many small functionalities being put together, it is more convenient to put together in an experiment space test

### 5.1 Random salt test 

<img width="231" height="376" alt="Screenshot 2026-05-27 at 10 33 35 AM" src="https://github.com/user-attachments/assets/d610cc11-abac-48d5-8106-9ea46ae0cc28" />

In the experiment space, random salt is selected continuously and the name of the salt is printed to the console to check for the correct oxidation state corresponding to the color of salt and the correct formatting of the salt name. The output satisfies both criteria. 

### 5.2 Bunsen burner and lighter test 

This test not only looks at the display of burner and lighter but also their interactions together as they have been grouped as “Heating equipment” and can be added and removed from experiment space simultaneously 

More information could be found in the demo video 

### 5.3 Components test 

Carried out to check whether limit on number of objects to be added is correctly implemented. Results show warning boxes popping up when the limit number is reached. 

# Reflections 

## Difficulties 

To simulate an actual quantitative analysis experiment, equipment needs to be created as FX Object. Every actual object needs at least one model class to manage its properties and one FX Object class to show on screen. 

For complicated ones like Bunsen burner or lighter, it requires more effort to put together already defined shape classes so that the result is somewhat pleasing for user interface. For test tubes, despite their simple structure, that is where most reactions take place, so visible changes need to be shown in parts of the test tube. Controller for test tubes become a lot more complex to other tools and sometimes too confusing. 

Additionally, interactions in chemistry between tools and substances are very complex and variable: different species have different observation with different other species, and it is almost impossible to accurately simulate them on JavaFX application using controls provided. 

## Learning points 

JavaFX is not recommended for building scientific simulations, especially for chemistry where species behavior does not follow any concrete formula. It might be more suitable for simulating phenomena that is more Math-related. 

Using MVC Structure for such chemistry application, however, is an extremely powerful tool to organize the project structure, especially when there are many classes involved (up to 20 classes). By doing the project, I understood more about the significance of such designing structure. 

Regular expression is a powerful tool to work with strings compared to string manipulation, which is lengthy and complicated. 

## Improvement 

Due to a limited time period for development, some functionalities like interactions between components were not as good as expected. The ideal plan was to be able to use coordinates for detecting interactions between components. However, it is decided that those navigations are to be done in further development of this application. 

The application is relatively hard coded because of the complex nature of chemistry. However, this could further be improved in the future with more time spent to figure out the logic to do so. 

CSS-styling could be thought more about. It was difficult to implement css – styling because it might affect the accuracy of displaying content. More thoughts on this are also possible in the future. 

# References and Citations for Project 

## Code-wise: 

1. Converting String to Color – StackOverflow
https://stackoverflow.com/questions/2854043/converting-a-string-to-color-in-java 

2. Name for application – ChatGPT August 3 version, GPT-3.5. Prompt: “suggest a cool name for a qualitative analysis simulator in chesmitry” → “another one”. 

3. Setting boundaries to the pane 
https://stackoverflow.com/questions/22683220/set-boundarylimit-for-draggable-pane-inside-its-parent-pane 

4. Adding external css file to javafx application
https://www.section.io/engineeringeducation/add-an-external-css-file-to-a-javafx-application/ 

5. How to style RadioButton with css stylesheet
https://stackoverflow.com/questions/55978223/how-to-change-color-of-selected-radiobuttonin-javafx-css 

6. Colors supported by JavaFX
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html 

7. Save/Load Dialogs, FileChooser in JavaFX - Code Noble Monkeys
https://www.youtube.com/watch?v 7lnVelyHxrc 

8. Open PDF files in Java – StackOverflow
https://stackoverflow.com/questions/16906831/howcan-i-launch-a-pdf-file-in-java 

## Other documents: 

1. Middle Tennessee State University
Quantitative Analysis Laboratory: https://www.mtsu.edu/chemistry/chem2230/pdfs/Lab_Rules.pdf 

