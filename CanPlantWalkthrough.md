# public boolean canPlant(String plantName, GardenRow row)  Walkthrough

Here is a walkthrough for canPlant(). There are a lot of ways you can do it, so if you did it a different way *that is fine*. My goal is to present a walkthrough that breaks the logic down into smaller steps. If you recall, a thought pattern we are learning in this course is **Divide**-**Conquer**-**Glue**.

* Divide: Take any problem, no matter how large and break it into smaller more mangagable parts. 
* Conquer: Solve those parts, if you can't, find ways to break it down more. 
* Glue: Assemble those parts back in away to solve the larger problem. 

The logic of canPlant() is hard enough that applying the divide-conquer-glue strategy is the best way to achieve success.

## Assumptions 
I am assuming you have completed `addRow(int row, int pH, String light)`. Remember, it is an extension (completing) of the code presented in the javadoc, and nothing more!

Also, after I completed addRow, I added additional tests to my `public static void main(String[] args)` inside of `GardenHelper.java`.

The code I personnally added are as follows:

```java

// Personal test - addRow() - filling all rows
System.out.println("Testing addRow(1, 7, partial sun):");
System.out.println("EXPECTING: true");
System.out.println("ACTUAL   : " + gardenHelperObj.addRow(1, 7, "partial sun"));
System.out.println();

// Personal test - addRow() - filling all rows
System.out.println("Testing addRow(3, 7, full sun):");
System.out.println("EXPECTING: true");
System.out.println("ACTUAL   : " + gardenHelperObj.addRow(1, 7, "full sun"));
System.out.println();
```

The addtional tests are just so I can make sure I have all three sun types in the rows for testing `canPlant()`.

> At this point, I submitted my code for grading! I got 3/4 for the grading, admittedly, that is because the last test isn't really running correctly. I do know I have 2/4 done with just addRow(), and I have a history of my files in the system. 


## Defining my Task for canPlant()

The first part of working on any method is defining my task. *Taking requirements, and putting them into my own words!*

The requirements in the [javadoc](https://www.cs.colostate.edu/~cs163/javadoc/lab05/GardenHelper.html) state:

> Checks if a particular plant is allowed to be planted where the user designates.

In my own words, I write in comments about the  method.

```java
// returns true if a plant can be planted. false if a plant can't be planted for any reason. - just checks conditions, nothing else!
```
This is my own words, you  may have a different way to look at it. It however gives me an idea of where to go. 

I then read the rest of the javadoc to better understand my remaining conditions

* Row must have correct sun coverage (full sun, partial sun, or any)
* Row must have a pH between the plant's pH range
* At least one row must have vegetables, so if it is the third row and the other two have flowers - can't plant a flower. 

The first two seem straight forward, the last one seems to be a bit harder. However, I write some psuedocode to help me figure out the conditions overall.

```java
// if rowSun is plantSun or plantSun is any - that is a true case
// if pH of row is between plant pH range (range means and!) that is a case for true
// if i have planted two other rows of flowers, my third row can't be flowers. 
```
Once again - just my way of better understanding the logic, but there is *power* in writing out what you understand and don't understand. 

## What do I have to work with?
For any task, before you start writing, you need to ask yourself what do you have to work with. The parameters are often the thing to start with:
* String plantName - this is just a String, but looking through the code, a GardenPlant has a lot more information than the String
* GardenRow row - this is a full `GardenRow`, which means I have all the methods that can help me. 

### Instance Variables
It is also worth checking the instance variables in `GardenHelper`:
* I notice there is one for every plant, though not sure if I need them.
* I notice there is one for each row, which  may be helpful for figuring out the third case of my logic.

### Useful methods
Looking through the code / javadoc, I am searching for methods that will 
1. help me figure out which `GardenPlant` from a String.
2. useful methods in `GardenPlant` and `GardenRow` that will help me figure out my logic of pH range, and sun cover. - The rest I don't care about. 

Based on that, I find the following methods:
* public GardenPlant searchPlant(String plantName) - This one takes in a String and returns a GardenPlant if the name matches, or null if it doesn't find a match! That saves me from having to figure that out myself.
* In GardenRow
    * public String getLight() - gets the light of the row, useful
    * public int getPH() - gets the pH of the row, as an int, useful
* In GardenPlant
    *  public String getType()  - this looks like it tells me VEG  or not, for the third logic
    * public int getpHLow()  - the low range of my pH
    * public int getpHHigh() - the high range of my pH
    * public String getAmountOfSunshine()  - the amount of sunshine needed

There may be more that I need, and sometimes you go looking as you need it! 

## Divide - Subtask 1: figure out which plant
My first subject, figure out which `GardenPlant` from the String provided. Since i have searchPlant(String plantName) that makes it easier for me. 

```java
GardenPlant plant = searchPlant(plantName);
// better add a 'safety' check in here
if(plant == null || row == null) return false; 
```
You will notice, I added a saftey check right away. If either of my Objects that I need are `null`, I simply returned false, as you can't plant something in a row if you don't have a plant or a row. This also makes the rest of my code a bit simpiler, as I can then assume both those Objects exist. 

## Divide - Subtask 2: Check Sunlight

I am going to **keep it simple**, and just check for the sunlight condition first. 

```java
// if rowSun is plantSun or plantSun is any - that is a true case
boolean goodSun = row.getLight().equals(plant.getAmountOfSunshine()) || plant.getAmountOfSunshine().equals("any");


// at the bottom I replace return false with
return goodSun;
```

### Testing
This is a great spot for me to run tests. I will have to think about tests that check sunlight, but may not be correct with pH. That is fine!

Looking at the test provided, I can check for that, so I run them as is, double checking the result.

## Divide - Subtask 3: Check pH
Now I am just going to check for the pH of the plant, to make sure the row is within the range.

```java
 boolean correctPH = (plant.getpHLow() <= row.getPH()) && 
                            (row.getPH() <= plant.getpHHigh());

// and since both light and pH must be correct I modify my return to the following
return goodSun && correctPH;
```
Something to note on this, I could have gone with `<` instead of `<=`, but when I ran the test in `GardenHelper.main` immediately after this - it failed! Looking through the default values of Carrot, I see the pH needs to be 6, and the row setup in the test is 6. This helped me adjust my logic. **If I tried coding the whole thing without running tests at each step, I wouldn't have caught this!** 


> At this point, I would turn in my code to zyBooks. I am checking two of the three conditions, and have tested those conditions. I am pretty confident I should at least keep my 3/4, but worth checking! I did end up with 4 of 4, with the last test failing now. That is closer, and I know the last test is probably checking the final and hardest condition, but it gives a full point no matter what I submit.  

This last part I modified after the lab came out, so if you got all but the last test, go resubmit to get full points on the last test. 


### Divide - Subtask 4: Check Other Rows

For this last bit, I want to break down the logic more. My solution is much more **robust** than the TA solution, as the TA solution actually had a slight error in the logic This solution fixes that error. 

* I need to find my other two rows (the hard part - and the error in the TA solution, they just checked all three rows every time).
* If either row is null, or the plant in the row is null, I am good to plant whatever! 
* Only then if there is something in both other rows, and there is somethign planted, and that planted something is not a vegetable in both, do I need to be concerned. 

This created two blocks of code. One that sets the other rows to tempory variables, which reduces a complex if/else statement check, and the other bit of code does the complex logic check. 


```java
GardenRow otherA = null, otherB = null;
if (row.equals(one)) {
   otherA = two;
   otherB = three;
} else if (row.equals(two)) {
   otherA = one;
   otherB = three;
} else if (row.equals(three)) {
   otherA = one;
   otherB = two;
}

boolean rowCheck = plant.getType().equals("VEG") ||
                  otherA == null || otherB == null ||  // the other rows are empty
                  (otherA != null && otherA.getPlant() == null) || // the rows exist but no plants
                  (otherB != null && otherB.getPlant() == null) ||
                  (otherA != null && otherA.getPlant() != null && otherA.getPlant().getType().equals("VEG"))||
                  (otherB != null && otherB.getPlant() != null && otherB.getPlant().getType().equals("VEG"));
        
return goodSun && correctPH && rowCheck; // updated statement
       
```

That was it! Take your time to go through it, and try to seek deeper understanding of every part. That will make you a better programmer, and you will find this starts to come more naturally in the future. I also added the following test to my main method

```java
  GardenHelper gardenHelper = new GardenHelper();
  gardenHelper.buildOptions();
  gardenHelper.addRow(1, 7, "any");
  gardenHelper.addRow(2, 7, "full sun");
  gardenHelper.addRow(3, 5, "partial sun");
  System.out.println(gardenHelper.addPlant("daisy", 1));
  System.out.println(gardenHelper.addPlant("begonia", 3));
  System.out.println(gardenHelper.addPlant("rose", 2));

```
Not overly wordy, but helped me compile and figure out if I had the right logic. 


## Glue - My final method solution

```java
public boolean canPlant(String plantName, GardenRow row){
   GardenPlant plant = searchPlant(plantName);
   if (plant == null || row == null) return false;
   boolean goodSun = row.getLight().equals(plant.getAmountOfSunshine())
                     || plant.getAmountOfSunshine().equals("any");
   boolean correctPH = (plant.getpHLow() <= row.getPH()) && 
                       (row.getPH() <= plant.getpHHigh());


   // This next part is a very *robust* version
   // and you will notice more drawn out, so you can see the steps I took 
   GardenRow otherA = null, otherB = null;
   if (row.equals(one)) {
     otherA = two;
     otherB = three;
   } else if (row.equals(two)) {
     otherA = one;
     otherB = three;
   } else if (row.equals(three)) {
     otherA = one;
     otherB = two;
   }

   boolean rowCheck = plant.getType().equals("VEG") ||
                  otherA == null || otherB == null ||  // the other rows are empty
                  (otherA != null && otherA.getPlant() == null) || // the rows exist but no plants
                  (otherB != null && otherB.getPlant() == null) ||
                  (otherA != null && otherA.getPlant() != null && otherA.getPlant().getType().equals("VEG"))||
                  (otherB != null && otherB.getPlant() != null && otherB.getPlant().getType().equals("VEG"));
        
   return goodSun && correctPH && rowCheck;
}
```

Needless to say, this code could actually be cleaned up by adding a private method for the last part! We hadn't introduced private methods yet, but I could have modified it as follows. 

```java
public boolean canPlant(String plantName, GardenRow row){
   GardenPlant plant = searchPlant(plantName);
   if (plant == null || row == null) return false;
   boolean goodSun = row.getLight().equals(plant.getAmountOfSunshine())
                     || plant.getAmountOfSunshine().equals("any");
   boolean correctPH = (plant.getpHLow() <= row.getPH()) && 
                       (row.getPH() <= plant.getpHHigh());


   boolean rowCheck = checkRowConditions(plant, row);        
   return goodSun && correctPH && rowCheck;
}

private boolean checkRowConditions(GardenPlant plant, GardenRow row) {
   GardenRow otherA = null, otherB = null;
   if (row.equals(one)) {
     otherA = two;
     otherB = three;
   } else if (row.equals(two)) {
     otherA = one;
     otherB = three;
   } else if (row.equals(three)) {
     otherA = one;
     otherB = two;
   }

   return plant.getType().equals("VEG") ||
                  otherA == null || otherB == null ||  // the other rows are empty
                  (otherA != null && otherA.getPlant() == null) || // the rows exist but no plants
                  (otherB != null && otherB.getPlant() == null) ||
                  (otherA != null && otherA.getPlant() != null && otherA.getPlant().getType().equals("VEG"))||
                  (otherB != null && otherB.getPlant() != null && otherB.getPlant().getType().equals("VEG"));

}

```

Overall, logic can get pretty hard, but the more we can break it down the easier it is! As a reminder, if you have a three of four, and struggled on the last part *THAT IS ALRIGHT!*. Go ahead and  submit now for 4 points, and come back later to really understand the logic. We all struggle as we learn, and if we aren't struggling, we aren't actually learning. 