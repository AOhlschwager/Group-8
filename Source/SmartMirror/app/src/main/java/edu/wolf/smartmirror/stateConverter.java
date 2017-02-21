package edu.wolf.smartmirror;

public class stateConverter {

    int id;

    public stateConverter(String state){
        id = 0;

        switch(state) {
            case "DC" :
                id = 21140;
                break;
            case "Alabama" :
                id = 21133;
                break;
            case "Alaska" :
                id = 21132;
                break;
            case "Arizona" :
                id = 21136;
                break;
            case "Arkansas" :
                id = 21135;
                break;
            case "California" :
                id = 21137;
                break;
            case "Colorado" :
                id = 21138;
                break;
            case "Connecticut" :
                id = 21139;
                break;
            case "Delaware" :
                id = 21141;
                break;
            case "Florida" :
                id = 21142;
                break;
            case "Georgia" :
                id = 21143;
                break;
            case "Hawaii" :
                id = 21144;
                break;
            case "Idaho" :
                id = 21146;
                break;
            case "Illinois" :
                id = 21147;
                break;
            case "Indiana" :
                id = 21148;
                break;
            case "Iowa" :
                id = 21145;
                break;
            case "Kansas" :
                id = 21149;
                break;
            case "Kentucky" :
                id = 21150;
                break;
            case "Louisiana" :
                id = 21151;
                break;
            case "Maine" :
                id = 21154;
                break;
            case "Maryland" :
                id = 21153;
                break;
            case "Massachusetts" :
                id = 21152;
                break;
            case "Michigan" :
                id = 21155;
                break;
            case "Minnesota" :
                id = 21156;
                break;
            case "Mississippi" :
                id = 21158;
                break;
            case "Missouri" :
                id = 21157;
                break;
            case "Montana" :
                id = 21159;
                break;
            case "Nebraska" :
                id = 21162;
                break;
            case "Nevada" :
                id = 21166;
                break;
            case "New Hampshire" :
                id = 21163;
                break;
            case "New Jersey" :
                id = 21164;
                break;
            case "New Mexico" :
                id = 21165;
                break;
            case "New York" :
                id = 21167;
                break;
            case "North Carolina" :
                id = 21160;
                break;
            case "North Dakota" :
                id = 21161;
                break;
            case "Ohio" :
                id = 21168;
                break;
            case "Oklahoma" :
                id = 21169;
                break;
            case "Oregon" :
                id = 21170;
                break;
            case "Pennsylvania" :
                id = 21171;
                break;
            case "Rhode Island" :
                id = 21172;
                break;
            case "South Carolina" :
                id = 21173;
                break;
            case "South Dakota" :
                id = 21174;
                break;
            case "Tennessee" :
                id = 21175;
                break;
            case "Texas" :
                id = 21176;
                break;
            case "Utah" :
                id = 21177;
                break;
            case "Vermont" :
                id = 21179;
                break;
            case "Virginia" :
                id = 21178;
                break;
            case "Washington" :
                id = 21180;
                break;
            case "West Virginia" :
                id = 21183;
                break;
            case "Wisconsin" :
                id = 21182;
                break;
            case "Wyoming" :
                id = 21184;
                break;
            default : id = 0;
        }
    }

    public String getId(){
        return Integer.toString(id);
    }

}
