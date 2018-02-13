package parameterssearching;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ExtraiPerformances {

	public static void main(String args[]) {

		int[] ensembleSizes = { 10, 15, 30, 50 };
		double[] fadingFactors = { 0.99, 0.9, 0.5 };

		String dataset = "fabric.arff";
		String cls = "UOB";
		for (int i = 0; i < ensembleSizes.length; i++) {
			for (int j = 0; j < fadingFactors.length; j++) {

				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("paramSettingsEval/"+dataset+"("+ensembleSizes[i]+"-"+fadingFactors[j ]+")"+cls+".csv"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				StringTokenizer strTok = null;

				Double recall0 = 0.0;
				Double recall1 = 0.0;
				Double gmean = 0.0;

				String strAux = "";
				int ct = 0;
				try {

					while ((strAux = br.readLine()) != null) {
						if (ct != 0) {
							strTok = new StringTokenizer(strAux, ",");

							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							strTok.nextToken();
							recall0 += new Double(strTok.nextToken());
							recall1 += new Double(strTok.nextToken());

						}
						ct++;
					}

					recall0 = recall0 / ct;
					recall1 = recall1 / ct;
					gmean = Math.sqrt(recall0 * recall1);

					System.out.println(recall0 + "\t" + recall1 + "\t" + gmean);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
