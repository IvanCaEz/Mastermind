
# Mastermind

## Description
This project consist of a game called Mastermind where a sequence
of 4 out of 5 colors is randomly generated and the user has X tries to guess the right sequence order.

Based on the order of the entered color it will display
a colored circle on that position. Displaying:
- A gray circle if the color doesn't exist in the sequence.
- A white circle if the color exists in the word but isn't in the right position.
- A black circle if the color exists and is in the right position.

---

## Install

Use git to clone this repository into your computer.

> git clone https://gitlab.com/ivan.martinez.7e6/mastermind

---

## Running the project

### Formatting the Output

In this project we will make use of variables to
color the foreground and background of each character
when needed.

#### Usage

* Write the name or names of the constants you want
  to use before the next word or character
* Use the variable `reset` after the desired word or character to end the formatting

---

## How To Play

- The user has 6 tries to guess the right sequence
- The user have to write 4 colors out of 5 available
- The colors of the sequence doesn't repeat themselves
- If the color is in the right position, will print a **black** circle to a history variable.
- If isn't in the right position will print a **white** one.
- If doesn't exist, will print a **gray** one.

---

### Code

The ***instructions*** function will explain the user how to play
and will ask the user to type ***START*** to begin.

### The Game

The ***codi*** function is where all the logic of the application lies.
- First will take a random color from the colorPool and will check if is present on the colorRandomList
- If the color isn't present, it will be added to the colorRandomList
- The program will repeat that until the size of the list is 4
- Then will ask the user for a color, if the color isn't one of the 5 availables or is repeated,
will return an error

#### When the user typed 4 colors:

- It will scan every color the user entered and will compare it with the order of the randomized sequence
- If the color is in the right position, will add a **black** circle to a history variable.
- If isn't in the right position will add a **white** one.
- If doesn't exist, will add a **gray** one.
- Then it will be added to a list called `historyList` and with each iteration will print the content
of the list creating the history of the game
- The program will also print the number of the current round.
- Last it will rest 1 try

##### When is the correct sequence:

Will print a congratulation message and will show the correct sequence.

##### When the user spends all their tries

Will end the programm and the game with a lose.

#### After the game

If the tries reach 0 or the user guesses the sequence it will ask if the user wants to continue playing,
read the rules or stop playing.
