package reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.StringUtils;

public class ReadingParser {
    public void trainAndWrite(String modelOutPath, String prop, String trainingFilepath) {
        Properties props = StringUtils.propFileToProperties(prop);
        props.setProperty("serializeTo", modelOutPath);

        //if input use that, else use from properties file.
        if (trainingFilepath != null) {
            props.setProperty("trainFile", trainingFilepath);
        }


        SeqClassifierFlags flags = new SeqClassifierFlags(props);


        CRFClassifier<CoreLabel> crf = new CRFClassifier<>(flags);
        crf.train();

        crf.serializeClassifier(modelOutPath);
    }

    @SuppressWarnings("rawtypes")
	public CRFClassifier getModel(String modelPath) {
        return CRFClassifier.getClassifierNoExceptions(modelPath);
    }

    @SuppressWarnings({ "rawtypes", "unused" })
	public void doTagging(CRFClassifier model, String input,int c)throws IOException {
        input = input.trim();
        String output=model.classifyToString(input);
        String lines[]=output.split("\n");
        String text[]= new String[lines.length];
        String label[]= new String[lines.length];
        String entities[]=new String[]{"Name","College Name", "Degree", "Graduation Year", "Years of Experience",
"Companies worked at","Designation","Skills", "Location","Email Address"};
           //Whatever the file path is.
           
            FileWriter w=new FileWriter("/home/agile/Desktop/JS Exapmles"+Integer.toString(c)+".txt",true);

        //System.out.println(output);
        for (String entity : entities)
        {
             //System.out.println(entity+":");
             int f=0;
             for(String line: lines)
             {
                  String tokens[]=line.split(" ");
                  for(String token: tokens)
                  {
                          String s[]=token.split("/");
                          if(s.length==2 && s[1].equals(entity) && f==1)
                              w.write(s[0]+" ");
                          if(s.length==2 && s[1].equals(entity) && f==0){
                              w.write(entity+":\n"+s[0]+" ");f=1;}
                   }
                   //System.out.println();
             }      
              w.write("\n");
        }
        System.out.println(c);
        w.close();

    }

    //Word Shape
    // samsung we344jj 32 gb ---> LLDDDLL

    @SuppressWarnings("unused")
	public static void main(String[] args)throws IOException {
        String propsFile ="/home/agile/Desktop/stanfordNER.prop";
        String trainfile ="/home//training.txt";
        String modelLoc = "/home/agile/Desktop/modelLoc.model";

        ReadingParser tagger = new ReadingParser();
        //tagger.trainAndWrite(modelLoc, propsFile, trainfile);

        @SuppressWarnings("rawtypes")
		CRFClassifier model = tagger.getModel(modelLoc);

        File location = new File("/home/agile/Desktop/JS Exapmles/");
        String type = ".txt"; // replace what ever type of file you need to search
        String testfiles[]=new String[20];
        int p=0;
        if (location.isDirectory() && location != null) {
            for (File f : location.listFiles()) {
                if (f.isFile() && f.getName().endsWith(type)) {
                    testfiles[p++]=f.getName();}
                }
        }
        int c=0;
        for(int i=0;i<20;i++)
        {
             File file = new File("/home/agile/Desktop/JS Exapmles/"+testfiles[i]); 
             PDDocument document = PDDocument.load(file); 
             PDFTextStripper pdfStripper = new PDFTextStripper();
             String text = pdfStripper.getText(document);
             document.close();
             tagger.doTagging(model, text,c);
             c++;
        }

        //Triple<Double,Double,Double> scores = model.classifyAndWriteAnswers("/home/abhishekn/Desktop/libs/test.txt", true);

    }

}