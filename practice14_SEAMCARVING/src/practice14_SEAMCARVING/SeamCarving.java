package practice14_SEAMCARVING;

import java.awt.Color;

import courseraclasses.Picture;

public class SeamCarving {
	Picture picture;
	Picture newPicture;
	int heigth;
	int width;
	double[] distTo;
	int[] edgeTo;
	double[] energy;

	public SeamCarving(Picture picture) {
		this.picture = picture;
		heigth = picture.height();
		width = picture.width();
	}

	// current picture
	public Picture picture() {
		return picture;
	}

	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return heigth;
	}

	// energy of pixel at column x and row y in current picture
	public double energy(int x, int y) {
		if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
			return 255 * 255 + 255 * 255 + 255 * 255;
		}
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		Color top = picture.get(x, y - 1);
		Color bottom = picture.get(x, y + 1);
		return getEnergy(left, right) + getEnergy(top, bottom);
	}

	private double getEnergy(Color first, Color second) {
		int R = Math.abs(first.getRed() - second.getRed());
		int G = Math.abs(first.getGreen() - second.getGreen());
		int B = Math.abs(first.getBlue() - second.getBlue());
		return R * R + G * G + B * B;
	}

	/**
	 * ������ � ������������� SEAM
	 **/

	// sequence of indices for vertical seam in current picture
	public int[] findVerticalSeam() {
		distTo = new double[heigth * width];
		edgeTo = new int[heigth * width];
		energy = new double[width * heigth];
		// �������� ����� ����� �������� � positive infinity �� ����
		for (int j = 0; j < width; j++) {
			for (int i = 0; i < heigth; i++) {
				// ��������� �������
				int n = width * i + j;
				// ���� �� 1 ������ �� �������� ������ �� ��������
				if (i == 0)
					distTo[n] = 0;
				// � �������� ������� �������� ������� ������
				else
					distTo[n] = Double.POSITIVE_INFINITY;
				// ��������� ������ � ��������� edgeTo ����������� ����������
				energy[n] = energy(j, i);
				edgeTo[n] = Integer.MIN_VALUE;
			}
		}

		// �������� �� �����
		for (int i = 0; i < heigth - 1; i++) {
			for (int j = 0; j < width; j++) {
				// ��������� �������
				int n = width * i + j;

				// �������� � ����� ���� ���� �����
				if (j - 1 >= 0)
					relax(n, width * (i + 1) + j - 1);

				// �������� � ����� ������ ���� �����
				if (j + 1 < width)
					relax(n, width * (i + 1) + j + 1);

				// �������� � �����, ����� ������
				relax(n, width * (i + 1) + j);
			}
		}

		/**
		 * ������� ����� � SEAM
		 */

		/**
		 * 1. �������� ������� � ��������� distTo � �������� �����
		 */
		int path[] = new int[heigth];
		double min = Double.POSITIVE_INFINITY;
		int start = edgeTo.length - width;

		for (int i = start, k = 0; i < edgeTo.length; i++, k++) {

			if (distTo[i] < min) {
				min = distTo[i];
				path[heigth - 1] = k;

			}
		}
		int e = (heigth - 1) * width + path[heigth - 1];

		/**
		 * 2. �������� ������� ���������� �������� � ����� �� ��������� edgeTo
		 */
		for (int i = path.length - 2; i > 0; i--) {
			path[i] = edgeTo[e] - i * width;
			e = edgeTo[e];
		}

		/**
		 * 3. �������� ������� � ��������� distTo � ��������� �����
		 */
		for (int i = 0; i < width; i++) {
			if (distTo[i] < min) {
				min = distTo[i];
				path[0] = i;
			}
		}

		return path;
	}

	// remove vertical seam from current picture

	public void removeVerticalSeam(int[] a) {
		int index = a[0];
		for (int i = 0; i < heigth; i++) {
			index = a[i];
			for (int j = 0; j < width - 1; j++) {
				if (j >= index)
					picture.set(j, i, picture.get(j + 1, i));
			}
		}
		this.width--;
		distTo = null;
		edgeTo = null;
		energy = null;
	}

	/**
	 * ������ � ��������������� SEAM
	 * 
	 * */

	// sequence of indices for horizontal seam in current picture
	public int[] findHorizontalSeam() {
		distTo = new double[heigth * width];
		edgeTo = new int[heigth * width];
		energy = new double[width * heigth];

		// �������� 1 �������� ������
		for (int i = 0; i < heigth; i++) {
			for (int j = 0; j < width; j++) {
				if (j == 0)
					distTo[width * i + j] = 0;
				else
					distTo[width * i + j] = Double.POSITIVE_INFINITY;

				energy[width * i + j] = energy(j, i);
				edgeTo[width * i + j] = Integer.MIN_VALUE;
			}
		}

		// �������� �����
		for (int j = 0; j < width - 1; j++) {
			for (int i = 0; i < heigth; i++) {
				int n = width * i + j;

				// �������� � ������� ���� ����
				if (i - 1 >= 0)
					relax(n, (width * (i - 1) + j + 1));

				// �������� � ����� ���� ����
				if (i + 1 < height())
					relax(n, (width * (i + 1) + j + 1));
				// �������� � ������
				relax(n, (width * (i) + j + 1));

			}
		}

		/**
		 * ������� ����� � SEAM
		 */

		/**
		 * 1. �������� ������� � ��������� distTo � ������� ���������
		 */

		int path[] = new int[width];
		double min = Double.POSITIVE_INFINITY;
		int start = width - 1;

		for (int i = start, k = 0; i < edgeTo.length; i += width, k++) {

			if (distTo[i] < min) {
				min = distTo[i];
				path[width - 1] = k;
			}
		}

		int e = path[width - 1] * width + width - 1;

		/**
		 * 2. �������� ������� ���������� �������� � ���������� �� ���������
		 * edgeTo
		 */
		for (int i = path.length - 2; i >= 0; i--) {
			path[i] = (edgeTo[e] - i) / width;
			e = edgeTo[e];
		}

		return path;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] a) {
		int index = a[0];
		for (int j = 0; j < width; j++) {
			index = a[j];
			for (int i = 0; i < heigth - 1; i++) {
				if (i >= index) {
					picture.set(j, i, picture.get(j, i + 1));
				}
			}
		}

		this.heigth--;
		distTo = null;
		edgeTo = null;
		energy = null;
	}

	/**
	 * ������� ���� ����������
	 * */
	public Picture create() {
		//width--;
		Picture p = new Picture(width, heigth);
		for (int row = 0; row < heigth; row++) {
			for (int col = 0; col < width - 1; col++) {
				p.set(col, row, picture.get(col, row));
			}
		}
		return p;
	}

	/**
	 * ������
	 * */
	private void relax(int v, int w) {
		if (distTo[w] > distTo[v] + energy[w]) {
			distTo[w] = distTo[v] + energy[w];
			edgeTo[w] = v;
		}
	}

}
