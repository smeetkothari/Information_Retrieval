/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Algo;

/**
 *
 * @author Smeet
 */
public class No_And_Position {

        private int fileno;
        private int position;

        public No_And_Position(int fileno, int position) {
            this.fileno = fileno;
            this.position = position;
        }

        public int getFileno() {
            return fileno;
        }

        public void setFileno(int fileno) {
            this.fileno = fileno;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "No And Position{" + "fileno=" + fileno + ", position=" + position + '}';
        }

    }
