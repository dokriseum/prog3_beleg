/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.cli;

import exceptions.NotImplementsException;
import logic.event.*;
import logic.utils.OutputSaver;
import models.mediaDB.Tag;
import models.mediaDB.UploaderImpl;
import models.storage.MediaType;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Input {
    private StringReader stringReader;
    private InputStreamReader isr;
    private BufferedReader br;
    private OutputStreamWriter osw;
    private BufferedWriter bw;
    private StringWriter sw;
    private String testString;
    private boolean exitCondition = false;
    private String inputString;
    private InputEventHandler handler;
    private Choice inputChoice;
    private InputEvent inputEvent;
    private DataInputStream streamInputData;
    private DataOutputStream streamOutputData;
    private StringBuffer output;

    public Choice getInputChoice() {
        return inputChoice;
    }

    public void setInputChoice(Choice inputChoice) {
        this.inputChoice = inputChoice;
    }

    public Input() {
        this.testString = "";
        stringReader = new StringReader(testString);
        br = new BufferedReader(stringReader);
        sw = new StringWriter();
        bw = new BufferedWriter(sw);
    }

    public Input(InputStream streamInput, OutputStream streamOutput) {
        if ((streamInput == null) && (streamOutput == null)) {
            this.testString = "";
            stringReader = new StringReader(testString);
            br = new BufferedReader(stringReader);
            sw = new StringWriter();
            bw = new BufferedWriter(sw);
        } else {
            this.streamInputData = new DataInputStream(streamInput);
            this.streamOutputData = new DataOutputStream(streamOutput);
        }
    }

    public boolean setTestString(String testString) {
        this.testString = testString;
        return true;
    }

    public InputEvent getInputEvent() {
        return inputEvent;
    }

    public void setHandler(InputEventHandler handler) {
        this.handler = handler;
    }

//
//    public boolean ioServer() {
//        try {
//            while (!exitCondition) {
//                System.out.println("Anfang IO " + this.inputChoice);
//                output = this.outputText();
//                System.out.println(output);
//
//                this.streamOutputData.writeUTF(output);
//                System.out.println("\nInputOutput -> output");
//
//                inputString = streamInputData.readUTF();
//
//                System.out.println("\t" + inputString);
//                if (inputString == null)
//                    break;
//                inputEvent = null;
//                try {
//                    inputEvent = inputMapping(inputString);
//                    if (this.handler != null) {
//                        handler.handle(inputEvent);
//                    }
//                    if (testString == null) {
//                        if ((inputEvent instanceof InputEventShowContent) ||
//                                (inputEvent instanceof InputEventShowUploader) ||
//                                (inputEvent instanceof InputEventAddContent) ||
//                                (inputEvent instanceof InputEventAddUploader) ||
//                                (inputEvent instanceof InputEventDeleteMedia) ||
//                                (inputEvent instanceof InputEventDeleteUploader)) {
//                            //this.waitingContinue();
//                        }
//                    }
//                } catch (IllegalArgumentException e) {
//                    System.err.println(e.getMessage());
//                } catch (NotImplementsException e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("InputOutput -> input");
//        return true;
//    }

    public void input() {
        try {
//            if (testString == null) {
//                isr = new InputStreamReader(System.in);
//                br = new BufferedReader(isr);
//                osw = new OutputStreamWriter(System.out);
//                bw = new BufferedWriter(osw);
//            } else {
//                stringReader = new StringReader(testString);
//                br = new BufferedReader(stringReader);
//                sw = new StringWriter();
//                bw = new BufferedWriter(sw);
//            }
            while (!exitCondition) {
                output = new StringBuffer();
                if ((this.streamInputData == null) && (this.streamOutputData == null)) {
                    bw.write(this.outputText());//System.out.println(this.outputText());
                    bw.flush();
                    inputString = br.readLine();
                } else {
                    if (OutputSaver.isIsShowEvent()) {
                        this.output.append(OutputSaver.getOutput());
                        OutputSaver.setIsShowEvent(false);
                    }
                    //System.out.println("Anfang IO " + this.inputChoice);
                    output.append(this.outputText());
                    //System.out.println(output);
                    this.streamOutputData.writeUTF(output.toString());
                    //TODO: entfernen
                    System.out.println("\nInputOutput -> output");

                    inputString = streamInputData.readUTF();
                }

                if (inputString == null)
                    break;
                inputEvent = null;
                try {
                    inputEvent = inputMapping(inputString);
                    if (this.handler != null) {
                        handler.handle(inputEvent);
                    }
                    if (testString == null) {
                        if ((inputEvent instanceof InputEventShowContent) ||
                                (inputEvent instanceof InputEventShowUploader) ||
                                (inputEvent instanceof InputEventAddContent) ||
                                (inputEvent instanceof InputEventAddUploader) ||
                                (inputEvent instanceof InputEventDeleteMedia) ||
                                (inputEvent instanceof InputEventDeleteUploader)) {
                            //this.waitingContinue();
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (NotImplementsException e) {
                    System.err.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    public void input(InputStream streamInput, OutputStream streamOutput) {
//        try {
//            this.isr = new InputStreamReader(streamInput);
//            this.br = new BufferedReader(this.isr);
//            this.osw = new OutputStreamWriter(streamOutput);
//            this.bw = new BufferedWriter(this.osw);
//            while (!exitCondition) {
//                bw.write(this.outputText());//System.out.println(this.outputText());
//                //bw.flush();
//                System.out.println("BR 1: " + br.readLine());
//                inputString = br.readLine();
//
//                if (inputString == null)
//                    break;
//                inputEvent = null;
//                try {
//                    inputEvent = inputMapping(inputString);
//                    if (this.handler != null) {
//                        handler.handle(inputEvent);
//                    }
//                } catch (IllegalArgumentException e) {
//                    bw.write(e.getMessage());
//                    bw.flush();
//                } catch (NotImplementsException e) {
//                    bw.write(e.getMessage());
//                    bw.flush();
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public InputEvent inputMapping(String inputString) throws IllegalArgumentException, NotImplementsException {

        if (inputString.equals("exit")) {
            exitCondition = true;
            return new InputEventExit(this, "InputEventExit");
        }

        //System.out.println("Hallo\n" + this.inputChoice + "\t" + inputString);
        String[] inputStringSplitted = inputString.trim().split(" ");
        if (inputStringSplitted[0].startsWith(":")) {
            switch (inputStringSplitted[0].trim()) {
                case ":c":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoiceADD", Choice.ADD);

                case ":d":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoiceDELETE", Choice.DELETE);

                case ":r":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoiceSHOW", Choice.SHOW);

                case ":u":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoiceEDIT", Choice.EDIT);

                case ":p":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoicePERSISTENCE", Choice.PERSISTENCE);

                case ":config":
                    return new InputEventSwitchChoice(this, "InputEventSwitchChoice", Choice.CONFIG);
                case ":exit":
                    exitCondition = true;
                    System.out.println("goodbye!");
                    System.exit(0);
                default:
                    throw new IllegalArgumentException("wrong input");
            }
        }

        if ((inputChoice == null) && !(inputString.equals("exit"))) {
            throw new IllegalArgumentException("please select the mode first");
        }

        switch (inputChoice) {
            case ADD:
                /**
                 * add Uploader
                 */
                if (inputStringSplitted.length == 1) {
                    return new InputEventAddUploader(this, "InputEventAddUploader", new UploaderImpl(inputStringSplitted[0]));

                }
                /**
                 * add Media
                 */
                if (inputStringSplitted.length >= 2) {
                    MediaType tempMediaType;
                    Collection<Tag> tags = new ArrayList<>();

                    switch (inputStringSplitted[0]) {
                        case "InteractiveVideo":
                            tempMediaType = MediaType.InteractiveVideo;
                            break;
                        case "LicensedAudioVideo":
                            tempMediaType = MediaType.LicensedAudioVideo;
                            break;
                        default:
                            throw new IllegalArgumentException("illegal type: " + inputStringSplitted[0]);
                    }
                    /*
                     * add the tags
                     */
                    if (!inputStringSplitted[2].equals(",")) {
                        String[] tagsSplitted = inputStringSplitted[2].trim().split(",");
                        for (String k : tagsSplitted) {
                            tags.add(Tag.valueOf(k));
                        }
                    }

                    /*
                     * Object source,
                     *                           String text,
                     *                           MediaType eventMediaType,      L1      I1
                     *                           int eventSamplingRate,         L10
                     *                           int eventWidth,                L8      I8
                     *                           int eventHeight,               L7      I7
                     *                           String eventEncording,         L6      I6
                     *                           String eventHolder,            L11
                     *                           long eventBitrate,             L4      I4
                     *                           Duration eventLength,          L5      I5
                     *                           BigDecimal eventSize,          Lx      Ix
                     *                           String eventAddress,           Lx      Ix
                     *                           Collection<Tag> eventTags,     L3      I3
                     *                           long eventAccessCount,         Lx      Ix
                     *                           Uploader eventUploader,        L2      I2
                     *                           Date eventUploadDate,          Lx      Ix
                     *                           String eventType)                      I9
                     *
                     */

                    if (tempMediaType == MediaType.LicensedAudioVideo) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                Integer.parseInt(inputStringSplitted[9]),
                                Integer.parseInt(inputStringSplitted[7]),
                                Integer.parseInt(inputStringSplitted[6]),
                                inputStringSplitted[5],
                                inputStringSplitted[10],
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                inputStringSplitted[8]
                        );
                    } else if (tempMediaType == MediaType.InteractiveVideo) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                -1,
                                Integer.parseInt(inputStringSplitted[7]),
                                Integer.parseInt(inputStringSplitted[6]),
                                inputStringSplitted[5],
                                null,
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                inputStringSplitted[8]
                        );
                    } else if (tempMediaType == MediaType.LicensedVideo) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                -1,
                                Integer.parseInt(inputStringSplitted[7]),
                                Integer.parseInt(inputStringSplitted[6]),
                                inputStringSplitted[5],
                                inputStringSplitted[8],
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                null
                        );
                    } else if (tempMediaType == MediaType.LicensedAudio) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                Integer.parseInt(inputStringSplitted[6]),
                                -1,
                                -1,
                                inputStringSplitted[5],
                                inputStringSplitted[8],
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                null
                        );
                    } else if (tempMediaType == MediaType.AudioVideo) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                Integer.parseInt(inputStringSplitted[8]),
                                Integer.parseInt(inputStringSplitted[7]),
                                Integer.parseInt(inputStringSplitted[6]),
                                inputStringSplitted[5],
                                null,
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                null
                        );
                    } else if (tempMediaType == MediaType.Video) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                -1,
                                Integer.parseInt(inputStringSplitted[7]),
                                Integer.parseInt(inputStringSplitted[6]),
                                inputStringSplitted[5],
                                null,
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                null
                        );
                    } else if (tempMediaType == MediaType.Audio) {
                        return new InputEventAddContent(this, "InputEventAddMedia",
                                tempMediaType,
                                Integer.parseInt(inputStringSplitted[6]),
                                -1,
                                -1,
                                inputStringSplitted[5],
                                null,
                                Long.parseLong(inputStringSplitted[3]),
                                Duration.ofSeconds(Long.parseLong(inputStringSplitted[4])),
                                tags,
                                new UploaderImpl(inputStringSplitted[1]),
                                new Date(),
                                null
                        );
                    } else {
                        throw new IllegalArgumentException("false media type");
                    }
                }
                break;
            case DELETE:
                boolean isMedia = false;
                for (MediaType k : MediaType.values()) {
                    if (inputString.contains(k.name())) {
                        isMedia = true;
                    }
                }

                if (!isMedia) {
                    return new InputEventDeleteUploader(this, "InputEventDeleteUploader", inputStringSplitted[0]);
                }
                if (isMedia) {
                    return new InputEventDeleteMedia(this, "InputEventDeleteMedia", inputStringSplitted[0]);
                }
                break;
            case SHOW:

                if (inputStringSplitted[0].equals("uploader")) {
                    return new InputEventShowUploader(this, "InputEventShowUploader");
                }
                if (inputStringSplitted[0].equals("content")) {
                    if (inputStringSplitted.length == 2) {
                        return new InputEventShowContent(this, "InputEventShowMediaByType", MediaType.valueOf(inputStringSplitted[1]));
                    } else if (inputStringSplitted.length == 1) {
                        return new InputEventShowContent(this, "InputEventShowMedia");
                    } else {
                        throw new IllegalArgumentException("illegal media type");
                    }
                }
                if (inputStringSplitted[0].equals("tag")) {
                    if (inputStringSplitted.length == 2) {
                        return new InputEventShowContent(this, "InputEventShowMediaTags", inputStringSplitted[1].charAt(0));
                    } else if (inputStringSplitted.length == 1) {
                        return new InputEventShowContent(this, "InputEventShowMediaTags");
                    } else {
                        throw new IllegalArgumentException("illegal media type");
                    }
                }
                break;
            case EDIT:
                return new InputEventUpdateContent(this, "InputEventUpdateContent", inputStringSplitted[0]);
            //throw new NotImplementsException("EDIT MODE is yet not implements");
            case CONFIG:
                throw new NotImplementsException("CONFIG MODE is yet not implements");
            case PERSISTENCE:
                if (inputStringSplitted[0].equals("saveJOS")) {
                    return new InputEventPersistence(this, "InputEventPersistence");
                }
                break;
            //throw new NotImplementsException("PERSISTENCE MODE is yet not implements");
            default:
                throw new IllegalArgumentException();
        }

        throw new IllegalArgumentException("not valid input");
    }

    private String outputTextForCommandSet() {
        return "###################################\n############# M E N U #############\n###################################\n\n" +
                ":c change to insert mode\n" +
                ":d change to delete mode\n" +
                ":r change to display mode\n" +
                ":u change to modification mode\n" +
                ":p change to persistence mode\n" +
                ":config change to config mode\n";
    }

    private String outputTextForInsert() {
        return "###################################\n########### I N S E R T ###########\n###################################\n\n" +
                "[producer name]\n\tadd a uploader\n" +
                "[media type] [producer name] [comma separated tags, single comma for none] [bitrate] [length] [[video encoding] [height] [width] [audio encoding] [sampling rate] [interaction type] [licensor]]\n" +
                "\tadd media content\n";
    }

    private String outputTextForDelete() {
        return "###################################\n########### D E L E T E ###########\n###################################\n\n" +
                "[producer name]\n\tdelete the producer\n" +
                "[retrieval address]\n\tdelete the media file\n";
    }

    private String outputTextForShow() {
        return "###################################\n############# S H O W #############\n###################################\n\n" +
                "uploader\n\tdisplay of the producers with the number of uploaded files\n" +
                "content [media type]\n\tdisplay of media files - possibly filtered by media type - with retrieval address, upload date and number of retrievals\n" +
                "tag [included (i) | excluded (e)]\n\tdisplay of existing or non-existing tags\n";
    }

    private String outputTextForEdit() {
        return "###################################\n############# E D I T #############\n###################################\n\n" +
                "[polling address]\n\tincreases the polling counter by one\n";
    }

    private String outputTextForPersistence() {
        return "###################################\n###### P E R S I S T E N C E ######\n###################################\n\n" +
                "[polling address]\n\tincreases the polling counter by one\n";
    }

    private String outputTextForConfig() {
        return "###################################\n########### C O N F I G ###########\n###################################\n\n" +
                "saveJOS\n\tsaves using JOS\n" +
                "loadJOS\n\tloads using JOS\n" +
                "saveJBP\n\tsaves with JBP\n" +
                "loadJBP\n\tloads using JBP\n" +
                "save [retrieval address]\n\tsaves a single instance into a file for all instances, if the file does not exist all instances are saved into a new one\n" +
                "load [retrieval address]\n\tloads a single instance from the file\n";
    }

    private String outputTextForRequestInput() {
        return "\nEnter input: ";
    }

    private String outputText() {
        String outputText = this.outputTextForCommandSet();
        if (this.inputChoice != null) {
            if (this.inputChoice.equals(Choice.ADD)) {
                outputText += this.outputTextForInsert();
            }
            if (this.inputChoice.equals(Choice.DELETE)) {
                outputText += this.outputTextForDelete();
            }
            if (this.inputChoice.equals(Choice.SHOW)) {
                outputText += this.outputTextForShow();
            }
            if (this.inputChoice.equals(Choice.EDIT)) {
                outputText += this.outputTextForShow();
            }
        }
        outputText += this.outputTextForRequestInput();
        return outputText;
    }

    private void waitingContinue() throws IOException {
        System.out.print("\ncontinue?: ");
        String tmpString = (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }
}
