****************************Goal of the project*********************************************
Our project is used to analyze the PCM matrices and answer the differents questions that are asked. To know

1 - Find the most common cell and see the values ​​that follow it most often
2- What are the sizes of the matrices?
3- What are the most common cell values?
4- Which cell values ​​are most often together in a column or row?
5- Is there a correlation between the predominant "type" of a "feature" and the cell values?
6- Are the values ​​of a column "homogeneous" ?
7- Can we identify "similar" matrices?
8- In which cases the extraction procedure is defective?

#For each question, we tried to answer the question through a CSV file produced during the analysis of PCM files. 
#these files will be saved in the csv folder
#
#***************************TECHNOLOGY USED******************************************************
#We used the OpenCompare java API for loading and retrieving data.
#
#
#***************************HOW TO INSTALL THE PROJECT ON YOUR COMPUTER **************************
#
#To install the project, follow these steps:
# - Download the .zip or clone the project in your local directory
# - Open the project in your IDE
# - The project is in the package src / main / java / org / opencompare / projectGroup1
#
# All classes necessary for operation are already included
#  In this package, you will see the files organized as follows:
# - There is a static function by class answering each question, so to use this function
# - In the main file, all functions are used.
# 
# *****************************LICENCE******************** ******
# This application is OPEN SOURCE and can be reused for any purpose of reuse
# 
# 
# 
#***********************************GOAL OF THE DIFFERENTS FUNCTIONS**************************************************
#2) The size of the matrices of a pcm is given by the multiplication of the size its characteristics and the size of its products.
#
#3) We started by analyzing a reduced "sample" of 10 pcm files, then we scaled our solution.
#The answer to this question was two steps:
#  Firstly the implementation of a java method which is used to browse a directory containing pcm files in order to calculate the number of 
#  occurrences of each cell, then generate a csv file containing the result.
#  Setting up an environment dedicated to the large-scale analysis (R) that reads the csv file and displays the first 
#10 cells that repeat most often (we limit ourselves to the top 10).
#
#4) Look for the words that follow each other the most
#   
#5) This function makes it possible to show that there is only correlation between the predominant "Boolean" and "NotAvailable" types
# of "feature" and the values of the cells. And not for the other predominant types of "feature" like String, Multiple etc.
#
#6) This function makes it possible to calculate the degree of homogeneity of all the columns of all the PCMs contained in a file, to say yes or not 
#if the columns are homogeneous and generates a file .CSV to visualize the result 
#(name of PCM, name of column "feature", the degree of homogeneity and non-homogeneity).
#For our assessment of these percentages we gave a 75% threshold. If the similarity exceeds this percentage the answer will be yes and no otherwise.
# 
# 7) The similarity of the matrices tries to compare the content or names of the features of each pcm in the set of files (1435). 
#   We will give their percentage of similarity as well as their percentage of difference.
#
# 8)Check if there are no defective dice
# 
# 
# 
# 
# 
# 
# 