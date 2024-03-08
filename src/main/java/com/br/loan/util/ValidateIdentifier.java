package com.br.loan.util;

import java.util.InputMismatchException;

import org.springframework.http.HttpStatus;

import com.br.loan.execption.BaseException;

public class ValidateIdentifier {
	
	
	public static Boolean validateCPF(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			return false;
		}

		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

			d1 = d1 + (11 - nCount) * digitoCPF;

			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		;

		resto = (d1 % 11);

		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		resto = (d2 % 11);

		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		return nDigVerific.equals(nDigResult);
	}

	public static Boolean validateCNPJ(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("-", "");
		cnpj = cnpj.replace("/", "");

		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) {
			return false;
		}

		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14))
			return (false);
		char dig13, dig14;
		int sm, i, r, num, peso;
		try {

			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}
			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
	    
	    public static Boolean validateEU(String eu) {
	    	
	    	if (eu == null || eu.length() != 8) {
	            return false;
	    	}
	    
	    	Integer verif = Integer.valueOf(String.valueOf(eu.charAt(0)))+Integer.valueOf(String.valueOf(eu.charAt(7)));
	    	
	    	if(verif!=9) {
	    		return false;
	    	}else {
	    		return true;
	    	}
	    	
	    }
	    
	    public static Boolean validateAP(String ap) {
	    	
	    	if(ap == null || ap.length()!=10) {
	    		return false;
	    	}
	    	
	    	String finalDigit = String.valueOf(ap.charAt(9));
	    	
	    	 for (int i = 0; i < (ap.length()-1); i++) {
	             
	    		 String a = String.valueOf(ap.charAt(i)); 
	             
	    		 if(a.equals(finalDigit)) {
	    			return false; 
	    		 }

	         }
	    	
	    	return true;
	    	
	    }
	    
	    
}

