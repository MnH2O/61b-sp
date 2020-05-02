import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture pic;
    private double[][] energy;

    public SeamCarver(Picture picture) {
        this.pic = picture;
        energy = new double[height()][width()];
        for (int col = 0; col < width(); col += 1) {
            for (int row = 0; row < height(); row += 1) {
                energy[row][col] = energy(col, row);
            }
        }
    }


    // current picture
    public Picture picture() {
        return pic;
    }

    // width of current picture
    public int width() {
        return pic.width();
    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width()-1 || y > height()-1) {
            throw new IndexOutOfBoundsException();
        }

        int Rx_PlusOne, Gx_PlusOne, Bx_PlusOne;
        int Rx_MinusOne, Gx_MinusOne, Bx_MinusOne;
        int Ry_PlusOne, Gy_PlusOne, By_PlusOne;
        int Ry_MinusOne, Gy_MinusOne, By_MinusOne;

        int RGBxPlusOne;
        int RGByPlusOne;
        int RGBxMinusOne;
        int RGByMinusOne;

        // corner cases
        if (x == 0) {
            RGBxMinusOne = picture().getRGB(width() - 1, y);
        } else {
            RGBxMinusOne = picture().getRGB(x - 1, y);
        }

        if (x == width() - 1) {
            RGBxPlusOne = picture().getRGB(0, y);
        } else {
            RGBxPlusOne = picture().getRGB(x + 1, y);
        }

        if (y == 0) {
            RGByMinusOne = picture().getRGB(x, height() - 1);
        } else {
            RGByMinusOne = picture().getRGB(x, y - 1);
        }

        if (y == height() - 1) {
            RGByPlusOne = picture().getRGB(x, 0);
        } else {
            RGByPlusOne = picture().getRGB(x, y + 1);
        }
        
        Rx_PlusOne = getR(RGBxPlusOne);
        Gx_PlusOne = getG(RGBxPlusOne);
        Bx_PlusOne = getB(RGBxPlusOne);
        Rx_MinusOne = getR(RGBxMinusOne);
        Gx_MinusOne = getG(RGBxMinusOne);
        Bx_MinusOne = getB(RGBxMinusOne);
        Ry_PlusOne = getR(RGByPlusOne);
        Gy_PlusOne = getG(RGByPlusOne);
        By_PlusOne = getB(RGByPlusOne);
        Ry_MinusOne = getR(RGByMinusOne);
        Gy_MinusOne = getG(RGByMinusOne);
        By_MinusOne = getB(RGByMinusOne);

        int Rx, Gx, Bx, Ry, Gy, By;
        int deltaX, deltaY;
        Rx = Rx_PlusOne - Rx_MinusOne;
        Gx = Gx_PlusOne - Gx_MinusOne;
        Bx = Bx_PlusOne - Bx_MinusOne;
        Ry = Ry_PlusOne - Ry_MinusOne;
        Gy = Gy_PlusOne - Gy_MinusOne;
        By = By_PlusOne - By_MinusOne;

        deltaX = Rx * Rx + Gx * Gx + Bx * Bx;
        deltaY = Ry * Ry + Gy * Gy + By * By;

        return (deltaX + deltaY);
    }
    
    private int getR(int rgb) {
        return (rgb)&0xFF;
    }

    private int getG(int rgb) {
        return (rgb>>8)&0xFF;
    }

    private int getB(int rgb) {
        return (rgb>>16)&0xFF;
    }  

    private double[][] transpose(double[][] original) {
        double[][] transpose = new double[original[0].length][original.length];
        for (int i = 0; i < original.length; i += 1) {
            for (int j = 0; j < original[0].length; j += 1) {
                transpose[j][i] = original[i][j];
            }
        }
        return transpose;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        energy = transpose(energy);
        int[] indices = findVerticalSeam();
        energy = transpose(energy);
        return indices;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int height = energy.length;
        int width = energy[0].length;
                
        double[][] energies = new double[height][width];

        energies[0] = energy[0];


        // finding the shortest path
        for (int j = 1; j < height; j += 1) {
            for (int i = 0; i < width; i += 1) {
                double energyThis = energy[j][i];
                double minValue = 0;
                double energyUpper = energies[j-1][i];

                // still we need to avoid corner cases
                if (i == 0) {
                    double energyUpperRight = energies[j-1][i+1];
                    if (energyUpper < energyUpperRight) {
                        minValue = energyUpper;
                    } else {
                        minValue = energyUpperRight;
                    }
                } else if (i == width - 1) {
                    double energyUpperLeft = energies[j-1][i-1];
                    if (energyUpper < energyUpperLeft) {
                        minValue = energyUpper;
                    } else {
                        minValue = energyUpperLeft;
                    }
                } else {
                    double energyUpperLeft = energies[j-1][i-1];
                    double energyUpperRight = energies[j-1][i+1];
                    minValue = (energyUpperLeft > energyUpper)? energyUpper:energyUpperLeft;
                    minValue = (minValue > energyUpperRight)? energyUpperRight:minValue;
                }

                energies[j][i] = minValue + energyThis;
            }
        }


        double minValueLastRow = Integer.MAX_VALUE;
        int minIndexLastRow = 0;
        for (int i = 0; i < width; i += 1) {
            double energyLastRow = energies[height - 1][i];
            if (energyLastRow < minValueLastRow) {
                minValueLastRow = energyLastRow;
                minIndexLastRow = i;
            }
        }

        int[] indices = new int[height];
        indices[height - 1] = minIndexLastRow;

        // get the shortest path
        for (int i = height - 2; i >= 0; i -= 1) {
            double upper = energies[i][indices[i+1]];
            if (indices[i+1] == 0) {
                double upperRight = energies[i][indices[i+1] + 1];
                indices[i] = (upper  < upperRight)? (indices[i+1]) : (indices[i+1] + 1);
            }

            else if (indices[i+1] == width - 1) {
                double upperLeft = energies[i][indices[i+1] - 1];
                indices[i] = (upper  < upperLeft)? (indices[i+1]) : (indices[i+1] - 1);
            }
            else {
                double upperRight = energies[i][indices[i+1] + 1];
                double upperLeft = energies[i][indices[i+1] - 1];
                double minValue;
                minValue = (upperLeft > upper)? upper:upperLeft;
                minValue = (minValue > upperRight)? upperRight:minValue;

                if (minValue == upper) {
                    indices[i] = indices[i+1];
                }
                else if (minValue == upperLeft) {
                    indices[i] = indices[i+1] - 1;
                }
                else {
                    indices[i] = indices[i+1] + 1;
                }
            }
        }

        return indices;
    }

    private boolean checkSeam(int[] seam) {
        for (int i = 1; i < seam.length; i += 1) {
            if ((seam[i] - seam[i - 1]) > 1) {
                return true;
            }
        }
        return false;
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || checkSeam(seam)) {
            throw new IllegalArgumentException();
        }

        pic = SeamRemover.removeHorizontalSeam(pic, seam);
        energy = new double[height()][width()];
        for (int col = 0; col < width(); col += 1) {
            for (int row = 0; row < height(); row += 1) {
                energy[row][col] = energy(col, row);
            }
        }
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || checkSeam(seam)) {
            throw new IllegalArgumentException();
        }

        pic = SeamRemover.removeHorizontalSeam(pic, seam);
        energy = new double[height()][width()];
        for (int col = 0; col < width(); col += 1) {
            for (int row = 0; row < height(); row += 1) {
                energy[row][col] = energy(col, row);
            }
        }
    }
}