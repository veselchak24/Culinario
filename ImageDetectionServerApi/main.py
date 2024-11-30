from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
import numpy as np
import cv2
from ultralytics import YOLO

app = FastAPI()

# Загрузка модели YOLOv8
model = YOLO('best (2).pt')

object_dict = {0: "персик", 1: "яблоко", 2: "капуста", 3: "кукуруза", 4: "огурец", 7: "баклажан",
               9: "чеснок", 10: "виноград", 11: "перец", 12: "авокадо", 13: "киви", 14: "лимон",
               15: "манго", 16: "лук красный", 21: "груша", 23: "банан", 24: "ананас", 28: "тыква",
               30: "клубника", 31: "помидор", 32: "редис", 33: "арбуз", 34: "свёкла", 35: "кабачок",
               36: "перец балгарский", 38: "брокколи", 39: "капуста", 40: "морковь"}

@app.post("/predict/")
async def predict(file: UploadFile = File(...)):
    print('yes')
    # Читаем изображение из файла
    contents = await file.read()
    np_array = np.frombuffer(contents, np.uint8)
    image = cv2.imdecode(np_array, cv2.IMREAD_COLOR)

    # Выполняем предсказание
    results = model(image)

    # Извлекаем названия классов
    pri = []
    for r in results:
        for c in r.boxes.cls:
            obj_index = int(model.names[int(c)])
            if obj_index in object_dict:
                pri.append(object_dict[obj_index])
    if len(pri):
        return JSONResponse(content={"fruit": pri})
    return JSONResponse(content={"error": "No fruit detected"})

@app.get("/")
async def main():
    return {"message": "Upload an image of a fruit to /predict/"}


if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
