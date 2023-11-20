import java.io.*;

public class Student {
    String student_path = "students.txt";

    public void delete_Student(String id) throws IOException{
        File std_file = new File(student_path);
        FileReader file_rdr = new FileReader(std_file);
        BufferedReader buff_rdr = new BufferedReader(file_rdr);

        String std_line;
        String[] std_fields;
        String tempPath =" temp.txt";
        String newln = System.getProperty("line.separator");

        File temp_file = new File(tempPath);
        FileWriter file_wrt = new FileWriter(temp_file);
        PrintWriter print_wrt = new PrintWriter(file_wrt);

        while((std_line = buff_rdr.readLine())!=null){
            std_fields = std_line.split(",");
            if(!std_fields[0].equals(id)){
                //print_wrt.printf("%4d,%-30s,%4.2f,%10s,%s%s",std_fields[0],std_fields[1],std_fields[2],std_fields[3],std_fields[4],newln);
                print_wrt.println(std_line);
            }


        }
        print_wrt.close();
        file_wrt.close();
        file_rdr.close();
        buff_rdr.close();

        std_file.delete();
        temp_file.renameTo(std_file);


    }

    public void add_Student(int id,String name,double cgpa,String dob,String gender) throws IOException{
        File std_file = new File(student_path);
        FileWriter file_wrt = new FileWriter(std_file, true);
        PrintWriter print_wrt = new PrintWriter(file_wrt);
        String newln = System.getProperty("line.separator");
        print_wrt.printf("%4s,%-30s,%4.2f,%10s,%s%s",id,name,cgpa,dob,gender,newln);

        print_wrt.close();
        file_wrt.close();

    }
    public void display_All() throws IOException{
        File std_file = new File(student_path);
        FileReader file_rdr = new FileReader(std_file);
        BufferedReader buff_rdr = new BufferedReader(file_rdr);

        String std_line;

        while((std_line = buff_rdr.readLine()) != null){
            System.out.println(std_line);
        }

        file_rdr.close();
        buff_rdr.close();

    }

    public void statistics() throws IOException{
        File std_file = new File(student_path);
        FileReader file_rdr = new FileReader(std_file);
        BufferedReader buff_rdr = new BufferedReader(file_rdr);
        String std_line;

        String output_path = "Stats.txt";

        File stats_file = new File(output_path);
        FileWriter file_wrt = new FileWriter(stats_file);
        PrintWriter print_wrt = new PrintWriter(file_wrt);
        String[] std_fields;
        int males = 0, females = 0, cgpa_sum = 0, avg;

        while((std_line = buff_rdr.readLine())!=null){
            std_fields = std_line.split(",");
            if(std_fields[4].equals("M")){
              males++;
            }
            else if(std_fields[4].equals("F"))
                females++;

            cgpa_sum += Double.valueOf(std_fields[2]);

        }
        System.out.println("There are "+males+" male students and "+females+" female students");
        System.out.printf("Class Average = %4.2f\n",(double)cgpa_sum/(males+females));
        print_wrt.println("There are : ");
        print_wrt.println(males + " male students");
        print_wrt.println(females + " female students");

        print_wrt.close();
        file_wrt.close();
        file_rdr.close();
        buff_rdr.close();
    }
}
