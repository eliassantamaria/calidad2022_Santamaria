package com.fca.calidad.calidad2022_Santamaria;

public class algebra {
	
	//calculadora xd
		private float ultimoResultado;
		public float suma(float primerSumando, float segundoSumando) {
			return ultimoResultado = primerSumando + segundoSumando;
		}
		public float resta(float minuendo, float sustraendo) {
			return ultimoResultado = minuendo - sustraendo;
		}
		public float multiplicacion(float primerFactor, float segundoFactor) {
			return ultimoResultado = primerFactor * segundoFactor;
		}
		public float division(float dividendo, float divisor) {
			return ultimoResultado = dividendo / divisor;
		}
			
		public int divisionEntera(int dividendo, int divisor)
		{
		return dividendo/divisor;
		}
		
		public float getUltimoResultado() {
			return ultimoResultado;
		}

}
