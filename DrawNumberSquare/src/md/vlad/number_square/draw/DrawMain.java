package md.vlad.number_square.draw;

import java.util.Scanner;

public class DrawMain {

	static int readInt() {
		int result = 0;
		Scanner scanner = new Scanner(System.in);
		String consoleInput = null;
		do {
			System.out.print("Write a number greater than 0 : ");
			consoleInput = scanner.nextLine();
			try {
				result = Integer.parseInt(consoleInput);
			} catch (NumberFormatException e) {
				System.err.print("WRITE AN INTEGER NUMBER GREATER THAN 0 !");
			}

		} while (result <= 0 || (consoleInput == null || consoleInput.isEmpty()));
		scanner.close();
		return result;
	}

	/**
	 * this method return the square root ROOT for a number SQUARE, where SQUARE
	 * is a the closest perfect square for the parameter 'number'and SQUARE is
	 * GREATER OR EQUAL TO 'number'
	 */
	static int squareRootCeil(int number) {
		int result = -1;
		double doubleRoot = Math.sqrt(number);
		int intRoot = (int) doubleRoot;
		if (doubleRoot > (double) intRoot) {
			result = (int) Math.ceil(doubleRoot);
		} else {
			result = intRoot;
		}
		return result;
	}

	static int spiralArrayHeight(int spiralLength, int minimalSpiralHeight, int inputNumber) {
		// the maximum for an array with dimension 'spiralLength',
		// 'minimalSpiralHeight' with consecutive numbers
		// for example, in an array[2,3] the maximum number will be 6
		int minimalArrayMax = spiralLength * minimalSpiralHeight;
		if (minimalArrayMax >= inputNumber) {
			return minimalSpiralHeight;
		} else {
			return minimalSpiralHeight + 1;
		}
	}

	static MyArrayElementPosition getInputNumberPosition(int length, int height, int input) {
		MyArrayElementPosition position = null;
		int x = 0;
		int y = 0;
		int square = length * length;
		int minimalSquare = height * height;
		int diff = square - input;
		// the spiral for perfect squares with even root will begin from the
		// left top.
		if (length % 2 == 0) {

			if (diff == 0) {
				x = 0;
				y = 0;
				position = new MyArrayElementPosition(x, y);
				return position;
			}

			if (diff < length) {
				y = diff;
				x = 0;
			} else {
				y = length - 1;
				x = height - (input - minimalSquare);
			}
		}
		// the spiral for perfect squares with odd root will begin from the
		// right bottom ,
		else {
			if (diff == 0) {
				x = length - 1;
				y = x;
				position = new MyArrayElementPosition(x, y);
				return position;
			}

			if (diff < length) {
				y = length - diff - 1;
				x = length - 1;
			} else {
				y = 0;
				x = input - minimalSquare - 1;
			}
		}
		position = new MyArrayElementPosition(x, y);
		return position;
	}

	static MyArrayElementPosition[] getElementPositions(MyArrayElementPosition initPos, int length, int height,
			int inputNumber) {
		MyArrayElementPosition[] array = new MyArrayElementPosition[inputNumber];
		array[0] = getStartPosition(length, height);
		array[inputNumber - 1] = getInputNumberPosition(length, height, inputNumber);

		return array;
	}

	static void completeArrayCounterClockSpiral(int inputNumber, int[][] array, MyArrayElementPosition startPosition,
			MyArrayElementPosition endPosition) {
		array[startPosition.getX()][startPosition.getY()] = 1;
		array[endPosition.getX()][endPosition.getY()] = inputNumber;
		int currentX = startPosition.getX();
		int currentY = startPosition.getY();
		int perfectSquare = 4; // next perfect square, after 1
		int perfectSquareRoot = 2;
		boolean goRight = true;
		boolean goUp = false;
		boolean goLeft = false;
		boolean goDown = false;
		for (int count = 2; count < inputNumber; count++) {
			if (goRight) {
				currentY++;
				array[currentX][currentY] = count;
				if (count == perfectSquareRoot) {
					goRight = false;
					goUp = true;
				}
				if (count == perfectSquare + 1) {
					goRight = false;
					goUp = true;
					perfectSquareRoot++;
					perfectSquare = perfectSquareRoot * perfectSquareRoot;
				}
				if (currentY == array[currentX].length - 1) {
					goRight = false;
					goUp = true;
				}
			} else if (goUp) {
				currentX--;
				array[currentX][currentY] = count;
				if (perfectSquare - count == perfectSquareRoot - 1) {
					goUp = false;
					goLeft = true;
				}
				if (currentX == 0) {
					goUp = false;
					goLeft = true;
				}
			} else if (goLeft) {
				currentY--;
				array[currentX][currentY] = count;
				if (count == perfectSquare + 1) {
					goLeft = false;
					goDown = true;
					perfectSquareRoot++;
					perfectSquare = perfectSquareRoot * perfectSquareRoot;
				}
				if (currentY == 0) {
					goLeft = false;
					goDown = true;
				}
			} else if (goDown) {
				currentX++;
				array[currentX][currentY] = count;
				if (perfectSquare - count < perfectSquareRoot) {
					goDown = false;
					goRight = true;
				}
				if (currentX == array.length - 1) {
					goDown = false;
					goRight = true;
				}
			}

		}
	}

	static boolean printComma(int array[][], int x, int y) {
		boolean result = true;
		if ((x == array.length - 1 && y == array[x].length - 1) || y == array[x].length - 1) {
			result = false;
		}
		if (y < array[x].length - 1) {
			if (array[x][y + 1] == 0) {
				result = false;
			}
		}

		return result;
	}

	public static void main(String[] args) {
			int i = readInt();
			int spiralLength = squareRootCeil(i);
			int minimalSpiralHeight = (int) Math.floor(Math.sqrt(i));
			// Calculate spiral array height
			int spiralHeight = spiralArrayHeight(spiralLength, minimalSpiralHeight, i);
			MyArrayElementPosition initPos = getStartPosition(spiralLength, spiralHeight);
			MyArrayElementPosition pos = getInputNumberPosition(spiralLength, spiralHeight, i);
			// calculate input number position in the spiral array
			int[][] array = new int[spiralHeight][spiralLength];
			completeArrayCounterClockSpiral(i, array, initPos, pos);
			for (int x = 0; x < array.length; x++) {
				for (int y = 0; y < array[x].length; y++) {
					if (array[x][y] == 0) {
						System.out.printf("%-8s"," ");
					} else {
						System.out.printf("%-8s",printComma(array, x, y) ? array[x][y] + "," : array[x][y]);
					}
				}
				System.out.println();
			}
	}

	static MyArrayElementPosition getStartPosition(int spiralLength, int spiralHeight) {
		int initY = (spiralLength % 2 == 0) ? (spiralLength / 2) - 1 : (int) Math.floor(spiralLength / 2);
		int initX = (spiralHeight % 2 == 0) ? (spiralHeight / 2) : (int) Math.ceil(spiralHeight / 2);
		MyArrayElementPosition initPos = new MyArrayElementPosition(initX, initY);
		return initPos;
	}

	/** start index will be 1 */
	static class MyArrayElementPosition {
		private int x;
		private int y;

		public MyArrayElementPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "MyArrayElementPosition [x=" + x + ", y=" + y + "]";
		}

	}

}
