public class Polynomial {
	
	private double[] coeffs;
	private double degree;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial() {
		double[] zeroPolynomial = new double[1];
		zeroPolynomial[0] = 0;
		this.coeffs = zeroPolynomial;
		this.degree = getDegree();

	}

	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		coeffs = coefficients;

	}

	/*
	 * Adds this polynomial to the given polynomial and returns the sum as a new
	 * polynomial.
	 */
	public Polynomial add(Polynomial polynomial) {
		double[] coeffs = this.coeffs;
		double[] addedCoeffs = polynomial.coeffs;
		double[] res = new double[Math.max(coeffs.length, addedCoeffs.length)];
		
		for (int i = 0; i <= this.getDegree(); i++) {
			res[i] += this.coeffs[i];
		}
		for (int j = 0; j <= polynomial.getDegree(); j++) {
			res[j] += polynomial.coeffs[j];
		}
		Polynomial addedPolynomial = new Polynomial(res);
		return addedPolynomial;
	}

	/*
	 * Multiplies this polynomial by c and returns the result as a new polynomial.
	 */
	public Polynomial multiplyByConstant(double c) {
		double[] coeffs = this.coeffs;
		double[] resCoeffs = new double[coeffs.length];
		for (int i = 0; i < coeffs.length; i++) {
			resCoeffs[i] = coeffs[i] * c;
		}
		Polynomial multiPolynomial = new Polynomial(resCoeffs);
		return multiPolynomial;
	}

	/*
	 * Multiplies this polynomial by the given polynomial and returns the result as
	 * a new polynomial.
	 */
	public Polynomial multiply(Polynomial polynomial) {
		double[] coeffs = this.coeffs;
		double[] multiCoeffs = polynomial.coeffs;
		double[] res = new double[coeffs.length + multiCoeffs.length - 1];
		for (int i = 0; i < coeffs.length; i++) {
			for (int j = 0; j < multiCoeffs.length; j++) {
				res[i + j] += (coeffs[i] * multiCoeffs[j]);
			}
		}
		Polynomial multiPolynomial = new Polynomial(res);
		return multiPolynomial;

	}

	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree() {
		int degree = 0;
		for (int i = 0; i < coeffs.length; i++) {
			if (coeffs[i] != 0) {
				degree = i;
			}
		}
		return degree;
	}

	/*
	 * Returns the coefficient of the variable x with degree n in this polynomial.
	 */
	public double getCoefficient(int n) {
		if (n <= this.getDegree()) {
			return coeffs[n];
		}
		return 0.0;
	}

	/*
	 * set the coefficient of the variable x with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of
	 * the variable x with degree n was 0, and now it will change to c.
	 */
	public void setCoefficient(int n, double c) {
		double[] coeffs = this.coeffs;
		if (this.degree >= n) {
			coeffs[n] = c;
		} else if (this.degree < n) {
			double[] setCoeffs = new double[n+1];
			for (int i = 0; i < coeffs.length; i++) {
				setCoeffs[i] = coeffs[i];
			}
			setCoeffs[n] = c;
			this.coeffs = setCoeffs;
		}
	}

	/*
	 * Returns the derivative of this polynomial. The first derivative of a
	 * polynomial a0x0 + ... + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	 * 
	 */
	public Polynomial getFirstDerivative() {
		double[] coeffs = this.coeffs;
		double[] deriveCoeffs = new double[coeffs.length - 1];
		for (int i = 0; i < coeffs.length - 1; i++) {
			deriveCoeffs[i] = coeffs[i+1]*(i+1);
		}
		Polynomial firstDerivative = new Polynomial(deriveCoeffs);
		return firstDerivative;
	}

	/*
	 * given an assignment for the variable x, compute the value of the polynomial
	 */
	public double computePolynomial(double x) {
		double result = 0.0;
		double[] coeffs = this.coeffs;
		for (int i = 0; i < coeffs.length; i++) {
			result += (coeffs[i] * (Math.pow(x, i))); 
		}
		return result;
	}

	/*
	 * given an assignment for the variable x, return true iff x is a root of this
	 * polynomial
	 */
	public boolean isARoot(double x) {
		double computeX = this.computePolynomial(x);
		if (computeX == 0.0) {
			return true;
		}
		return false;

	}

}
