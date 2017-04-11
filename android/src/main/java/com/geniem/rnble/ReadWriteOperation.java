package com.geniem.rnble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGatt;


class ReadWriteOperation {

    public static enum Type {
        READ, WRITE
    }

    private Type type;
    private BluetoothGattCharacteristic characteristic;
    private int timeoutCount = 0;

    /**
     * Create new read/write operation. Holds type (read / write )
     * and the ble characteristic
     */
    ReadWriteOperation(Type type, BluetoothGattCharacteristic characteristic) throws IllegalArgumentException {
        this.characteristic = characteristic;
        this.type = type;
        if ( type == null || characteristic == null ) throw new IllegalArgumentException("ReadWriteOperation() requires type and characteristic");
    }

    /**
     *  READ / WRITE
     */
    Type getType() {
        return type;
    }

    /** 
     * Increments timeout counter and returns timeout count
     * after the incrementation
    */
    public int incrementTimeout() {
        ++ timeoutCount;
        return timeoutCount;
    }
    
    BluetoothGattCharacteristic getChar() {
        return characteristic;
    }

    void handle(BluetoothGatt bluetoothGatt) {
        if ( type == Type.READ ) {
            bluetoothGatt.readCharacteristic(characteristic);
        }
        else if ( type == Type.WRITE ) {
            bluetoothGatt.writeCharacteristic(characteristic);
        }
        else {
            throw new RuntimeException("ReadWriteOperation has no type");
        }
    }
}