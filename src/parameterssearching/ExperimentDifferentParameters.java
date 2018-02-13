package parameterssearching;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.jfree.data.gantt.Task;

import moa.options.ClassOption;
import moa.tasks.EvaluatePrequential;
import moa.tasks.MainTask;
import moa.tasks.TaskThread;

public class ExperimentDifferentParameters {

	static MainTask currentTask = new EvaluatePrequential();
	static Writer writer; 
	
	public ExperimentDifferentParameters(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] ensembleSizes = {10, 15, 30, 50}; 
		double[] fadingFactors = {0.99, 0.9, 0.5};
		
		String dataset = "fabric.arff";
		String cls = "UOB";
		for(int i = 0; i < ensembleSizes.length; i++){
			for(int j = 0; j < fadingFactors.length; j++){
				
				//writer = new FileWriter("parametersSettings/"+dataset+i+"-"+j+cls+".txt");
				
				String task = "EvaluatePrequential -l (meta.UOB -s "+ensembleSizes[i]+")  -s  (ArffFileStream -f (/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/arffs/"+dataset+") -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a "+fadingFactors[j]+") -f 1 -d /Users/georgegomescabral/ProjetosSoftwares/Java/workspace/PaperMarch2018/paramSettingsEval/"+dataset+"("+ensembleSizes[i]+"-"+fadingFactors[j]+")"+cls+".csv";
				System.out.println(task);
				try {
		            currentTask = (MainTask) ClassOption.cliStringToObject(
		                    task, MainTask.class, null);
		            String taskText = currentTask.getCLICreationString(MainTask.class);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				
				TaskThread thread = new TaskThread((moa.tasks.Task) currentTask);

				thread.start();
			}
		}
		
		//String task = "EvaluatePrequential -l (meta.WaitForLabelsUOB  -t 0.99)  -s (ArffFileStream -f (/Users/georgegomescabral/ProjetosSoftwares/Java/workspace/MOA/datasets/jruby_paper_attributes_plus_timestamp.arff) -c 15) -e (FadingFactorEachClassPerformanceEvaluator -a 0.99) -f 1 -d /Users/georgegomescabral/ProjetosSoftwares/Java/workspace/MOA/results/jruby-ARF.csv";
		
		
	}
	
}
