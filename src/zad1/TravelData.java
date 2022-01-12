package zad1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TravelData {

    private String descriptionLine;
    private String strOffers[] = null;
    private int numberOfDescription = 0;
    private File dataDir;
    private String language;

    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat)   {
        List<String> offersList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dataDir));
            descriptionLine = reader.readLine();


            while (descriptionLine != null){
                strOffers = descriptionLine.split("\t");
                language = strOffers[0];
                if(language.equals(loc.toString())) {
                    offersList.add(String.valueOf(new OffersDescriptions(numberOfDescription, strOffers[0], strOffers[1], strOffers[2], strOffers[3], strOffers[4], strOffers[5], strOffers[6])));
                    numberOfDescription++;
                }
                descriptionLine = reader.readLine();
            }
            System.out.println("---------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return offersList;
    }
}
