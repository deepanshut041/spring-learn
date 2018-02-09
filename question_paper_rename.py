import pandas as pd
import os
# This function reads all file name an
# def read_all_file_names():
    
#     # for name in file_names:
#     #     print(name)

class PaperRenamer:
    file_names = []
    processed_codes = []
    processed_names = []
    excel_output = None
    df = None
    path = "C:/Lectures/process"

    # This function reads name from excel and convert it into dictonary
    def read_excel_to_dictonary(self):
        self.df = pd.read_csv('data.csv')
        parsed = self.df.drop_duplicates(subset='subject_code', keep="last")
        self.excel_output = parsed
    # This function parse name required returns it for searching 
    def parse_required_name(self):
        file_names = os.listdir(self.path)
        self.file_names = file_names
        for file_name in file_names:
            code_start = 0
            code_end = 0
            for i in range(0,len(file_name)-4):
                zeoth_digit = "" +  file_name[i]
                first_digit = "" + file_name[i+1]
                second_digit = "" + file_name[i+2]
                third_digit = "" + file_name[i+3]
                fourth_digit = "" + file_name[i+4]
                if ((not zeoth_digit.isdigit()) and (first_digit.isdigit())
                    and (second_digit.isdigit()) and (third_digit.isdigit())
                    and (not fourth_digit.isdigit())
                    ):
                    code_start = i + 1
            branch_code = " "
            if code_start != 0:
                if file_name[code_start-1].isalpha():
                    branch_code = file_name[code_start-2] + file_name[code_start-1] + branch_code + file_name[code_start:code_start+3]
                elif file_name[code_start-2].isalpha():
                    branch_code = file_name[code_start-3] + file_name[code_start-2] + branch_code + file_name[code_start:code_start+3]
                elif file_name[code_start-3].isalpha():
                    branch_code = file_name[code_start-4] + file_name[code_start-3] + branch_code + file_name[code_start:code_start+3]
                elif file_name[code_start-4].isalpha():
                    branch_code = file_name[code_start-5] + file_name[code_start-4] + branch_code + file_name[code_start:code_start+3]
            self.processed_codes.append(branch_code)

    # This function search name and then renames it
    def file_renamer(self, old_name, new_name):
        old_name_split = old_name.split(".")
        try:
            os.rename(self.path + "/" + old_name, self.path + "/" + new_name + "." + old_name_split[-1])
            return 0
        except:

            return 1

    # This function matches subject code
    def name_matcher(self):
        count_false = 0
        count_true = 0
        for i in range(len(self.processed_codes)):
            new_name = ""
            for j in self.excel_output.index:
                row = self.excel_output.ix[j]
                if row['subject_code'] == self.processed_codes[i]:
                    new_name = row['subject_name'] + " " + row['subject_code']
                    print(self.file_renamer(self.file_names[i], new_name))
                    


            if new_name == "":
                count_false += 1
            else:
                count_true += 1
        print("False : ", count_false)
        print("True : ", count_true)
                #     pass
                    # print(self.excel_output['subject_name'][j])

    #This is main function it mantains all functions
    def main(self):
        # read_all_file_names()
        self.parse_required_name()
        self.read_excel_to_dictonary()
        self.name_matcher()

paperRenamer = PaperRenamer()
paperRenamer.main()

