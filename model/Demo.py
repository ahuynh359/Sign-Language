import cv2
from cvzone.HandTrackingModule import HandDetector
import numpy as np
import math
import time
from tensorflow import keras

file_path_model=r'model_97_extra(100p).h5'
model=keras.models.load_model(file_path_model)
# classes=['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'unknown']
# classes=['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
classes=['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

#Hàm dự đoán input: hình ảnh xám (size,size,1), output: vị trí nhãn trong classes, xác suất
def predict(img,model,size=28):
    img=np.array(img)
    img=np.uint8(img)
    img=img/255.0
    img=img.reshape(1,size,size,1)
    pred_arr=model.predict(img)
    y_pred=np.argmax(pred_arr,axis=1)[0]
    return y_pred, pred_arr[0][y_pred]
if __name__ =='__main__':
    folder = "Data/A"
    offset=20
    img_size=300
    pred_size=100
    count=0
    cap = cv2.VideoCapture(0)
    detector= HandDetector(maxHands=1)
    while(True):
        ret, frame=cap.read()
        if ret:
            hands,frame=detector.findHands(frame,draw=False)
            if hands:
                hands=hands[0]
                x,y,w,h=hands['bbox']
                if x-offset < 0 or y - offset <0:
                    cv2.imshow('Video sign language with dot', frame)
                    continue
                img_white=np.ones((img_size,img_size,3),np.uint8)*0
                img_hand=frame[y-offset:y+h+offset,x-offset:x+w+offset]
                # img_hand_shape=img_hand.shape
                try:
                    aspect_ratio=h/w
                    if aspect_ratio>1:
                        k = img_size/h
                        w_cal=math.ceil(k*w)
                        img_resize=cv2.resize(img_hand,(w_cal,img_size))
                        img_resize_shape=img_resize.shape
                        w_gap=math.ceil((img_size-w_cal)/2)
                        img_white[:,w_gap:w_cal+w_gap]=img_resize
                    else:
                        k = img_size/w
                        h_cal=math.ceil(k*h)
                        img_resize=cv2.resize(img_hand,(img_size,h_cal))
                        img_resize_shape=img_resize.shape
                        h_gap=math.ceil((img_size-h_cal)/2)
                        img_white[h_gap:h_cal+h_gap,:]=img_resize
                    img_white=cv2.cvtColor(img_white,cv2.COLOR_RGB2GRAY)
                    # img_white=cv2.bitwise_not(img_white)
                    cv2.imshow("ImageHand",img_white)
                    cv2.rectangle(frame,(x-offset,y-offset),(x+w+offset,y+h+offset),color=(0,0,255),thickness=2)
                    
                    img_white=cv2.resize(img_white,(pred_size,pred_size))
                    
                    y_pred, acc_y=predict(img_white,model,pred_size)
                    cv2.putText(frame,classes[y_pred] +'_'+str(round(acc_y,2)),(x,y-offset),cv2.FONT_HERSHEY_SIMPLEX,1,color=(0,0,255),thickness=2)
                except Exception as e:
                    print(e)
                    cv2.putText(frame,"Error",(50,50),cv2.FONT_HERSHEY_SIMPLEX,1,color=(0,0,255),thickness=2)
            cv2.imshow('Video sign language with dot', frame)
        key = cv2.waitKey(10)
        if key == ord ('q'):
            break
        if key == ord('s'):
            count+=1
            cv2.imwrite(f'{folder}/Hand_{time.time()}.jpg',img_white)
            print('saved: ',count)
    cap.release()
    cv2.destroyAllWindows()