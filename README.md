# Dhwani

An on-device Android call assistant for deaf and hard-of-hearing users.

## Features

- Offline English and Hindi live captions
- Typed and smart replies spoken through Android TTS
- Personal context, call briefings, and encrypted summaries
- Front-camera Indian Sign Language input
- On-device Gemma, MediaPipe, and ONNX Runtime inference

## Requirements

- Android Studio with JDK 17
- Android SDK 36
- Android 8.0+ arm64 phone with USB debugging

## Setup

```bash
git clone https://github.com/aaditya3301/dhwani.git
cd dhwani
```

Open the project in Android Studio and let Gradle sync. Android Studio creates
`local.properties`; confirm that it points to your Android SDK.

Add the optional caption and assistant models:

```text
app/src/main/assets/vosk-en/                         English captions
app/src/main/assets/vosk-hi/                         Hindi captions
app/src/main/assets/models/gemma3-1b-it-int4.task   Smart features
```

Use the Vosk English India and Hindi models from
[Vosk](https://alphacephei.com/vosk/models). Download the Gemma task file from
[Gemma3-1B-IT](https://huggingface.co/litert-community/Gemma3-1B-IT).

Sign-recognition assets are already included.

## Run

Connect the phone, accept the USB debugging prompt, then run:

```powershell
.\gradlew.bat installDebug
```

On macOS or Linux:

```bash
./gradlew installDebug
```

You can also select the phone in Android Studio and click **Run**.

## Use

1. Put the phone call on speaker.
2. Open **Live** and start captions.
3. Type, choose, or sign a reply, then tap **Speak**.

Camera access is requested only for sign input. Call data and inference stay on
the device.

## Models

Sign recognition uses a hybrid offline pipeline. Google's MediaPipe Gesture
Recognizer provides generic hand-gesture fallback. ISL recognition uses a
10-class Transformer fine-tuned from the small pretrained checkpoint in the
[AI4Bharat INCLUDE repository](https://github.com/AI4Bharat/INCLUDE) and
exported to FP32 ONNX. It reads up to 169 frames containing the first 25 pose
landmarks and all 21 landmarks from each hand. The supported model labels are
`BATHROOM`, `CELLPHONE`, `DOCTOR`, `HELLO`, `HOSPITAL`, `MEDICINE`, `MONEY`,
`PATIENT`, `SICK`, and `THANKYOU`. A result must agree across temporal views
and pass confidence and class-separation checks; otherwise it is shown as
unknown.

Tap **Recognize sign**, move the phone back until your shoulders and hands are
visible, then perform one complete sign once. The app recognizes isolated
signs, not continuous sign-language sentences. It may take up to eight seconds
to collect enough frames on a slower phone.
