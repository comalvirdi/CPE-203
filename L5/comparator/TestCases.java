import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator comparator = new ArtistComparator();
      assertTrue(comparator.compare(songs[0], songs[1]) < 0);
      assertTrue(comparator.compare(songs[2], songs[6]) < 0);
      assertTrue(comparator.compare(songs[6], songs[2]) > 0);
      assertTrue(comparator.compare(songs[2], songs[2]) == 0);
      
   }


   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> titleComparator = (Song s1, Song s2) -> (s1.getTitle().compareTo(s2.getTitle()));
      assertTrue(titleComparator.compare(songs[0], songs[1]) > 0);
      assertTrue(titleComparator.compare(songs[2], songs[6]) > 0);
      assertTrue(titleComparator.compare(songs[6], songs[2]) < 0);
      assertTrue(titleComparator.compare(songs[2], songs[2]) == 0);
   }


   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> yearComparator = Comparator.comparing(Song::getYear);
      Comparator<Song> yearComparatorReversed = yearComparator.reversed();
      assertTrue(yearComparatorReversed.compare(songs[0], songs[1]) == 0);
      assertTrue(yearComparatorReversed.compare(songs[1], songs[2]) > 0);
      assertTrue(yearComparatorReversed.compare(songs[6], songs[7]) > 0);
      assertTrue(yearComparatorReversed.compare(songs[7], songs[0]) > 0);
      assertTrue(yearComparatorReversed.compare(songs[0], songs[7]) < 0);
   }


   @Test
   public void testComposedComparator()
   {
      Comparator<Song> titleComparator = (Song s1, Song s2) -> (s1.getTitle().compareTo(s2.getTitle()));
      Comparator<Song> yearComparator = Comparator.comparing(Song::getYear);
      Comparator<Song> titleOrYear = new ComposedComparator(titleComparator, yearComparator);
      Song[] test1= new Song[] {songs[5], songs[7]};
      Arrays.sort(test1, titleOrYear);
      Song[] expected1 = new Song[] {songs[7], songs[5]};
      assertTrue(test1[0] == expected1[0]);
      assertTrue(test1[1] == expected1[1]);
   
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> thenComparing = Comparator.comparing(Song::getTitle, (s1, s2) -> s1.compareTo(s2)).thenComparing(new ArtistComparator());
      assertTrue(thenComparing.compare(songs[3], songs[5]) > 0);
      Song[] test1= new Song[] {songs[5], songs[3]};
      Arrays.sort(test1, thenComparing);
      Song[] expected1 = new Song[] {songs[5], songs[3]};
      assertTrue(test1[0] == expected1[0]);
      assertTrue(test1[1] == expected1[1]);
   }


   @Test
   public void runSort()
   {
      Comparator<Song> thenComparing = Comparator.comparing(Song::getArtist, (s1, s2) -> s1.compareTo(s2))
      .thenComparing(Song::getTitle, (s1, s2) -> s1.compareTo(s2)).thenComparing(Song::getYear, (s1, s2) -> s1.compareTo(s2));

      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      songList.sort(thenComparing);

      assertEquals(songList, expectedList);
   }
}
