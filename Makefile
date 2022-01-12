# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: Balls TestBalls BallsSimulator TestBallsSimulator TestGUI TestGUI1 Conway TestConway Grille Immigration TestImmigration Schelling TestSchelling

Balls:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/Balls.java

TestBalls:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBalls.java

BallsSimulator:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/BallsSimulator.java

TestBallsSimulator:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator.java

TestGUI:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java

TestGUI1:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI1.java

Grille:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/Grille.java

Conway:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/Conway.java

TestConway:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestConway.java

Immigration:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/Immigration.java

TestImmigration:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestImmigration.java

Schelling:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/Schelling.java

TestSchelling:
		javac -d bin -classpath bin/gui.jar -sourcepath src src/TestSchelling.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM

exeBalls:
		java -classpath bin:bin/gui.jar TestBalls

exeBallsSimulator:
		java -classpath bin:bin/gui.jar TestBallsSimulator

exeGUI:
		java -classpath bin:bin/gui.jar TestGUI

exeGUI1:
		java -classpath bin:bin/gui.jar TestGUI1

exeConway:
		java -classpath bin:bin/gui.jar TestConway

exeImmigtation:
		java -classpath bin:bin/gui.jar TestImmigration

exeSchelling:
		java -classpath bin:bin/gui.jar TestSchelling

clean:
		rm -rf bin/*.class
