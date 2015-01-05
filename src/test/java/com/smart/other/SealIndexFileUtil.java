package com.smart.other;

import org.apache.commons.lang3.ObjectUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SealIndexFileUtil {

    private static final String sealIndexFileName = "C:\\Users\\wang\\Desktop\\sealIndex.ini";
    private static final String sealIndexFileNameNew = "C:\\Users\\wang\\Desktop\\sealIndexNew.ini";

    public static void writeSealIndex(List<SealDto> sealDtoList) {
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(sealIndexFileName));
            objectOutputStream.writeObject(sealDtoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeSealIndexNew(List<SealDto> sealDtoList) {
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(sealIndexFileNameNew));
            for (int i = 0, size = sealDtoList.size(); i < size; i++) {
                SealDto sealDto = sealDtoList.get(i);
                objectOutputStream.writeObject(sealDto);
                //System.out.println(sealDto);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeSealIndex(SealDto sealDto) {
        List<SealDto> sealDtoList = readSealIndex();
        sealDtoList.add(sealDto);
        writeSealIndex(sealDtoList);
    }

    public static void writeSealIndexNew(SealDto sealDto) {
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new AppendingObjectOutputStream(new FileOutputStream(sealIndexFileNameNew, true));
            objectOutputStream.writeObject(sealDto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<SealDto> readSealIndex() {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(sealIndexFileName));
            List<SealDto> sealDtoList = (List<SealDto>) objectInputStream.readObject();
            return sealDtoList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<SealDto>();
    }

    public static List<SealDto> readSealIndexNew() {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(sealIndexFileNameNew));
            List<SealDto> sealDtoList = new ArrayList<SealDto>();

            try {
                SealDto sealDto = null;
                while ((sealDto = (SealDto) objectInputStream.readObject()) != null) {
                    //System.out.println(sealDto);
                    sealDtoList.add(sealDto);
                }
            } catch (java.io.EOFException e) {
                //e.printStackTrace();
            }

            return sealDtoList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<SealDto>();
    }

    public static void modifySealIndexNew(SealDto sealDto) {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(sealIndexFileNameNew));

            try {
                SealDto oldSealDto = null;
                while ((oldSealDto = (SealDto) objectInputStream.readObject()) != null) {
                    if(ObjectUtils.equals(oldSealDto, sealDto)){
                        oldSealDto.setValid(1);
                    }
                }
            } catch (java.io.EOFException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<SealDto> sealDtoList = new ArrayList<SealDto>();
        for (int i = 0; i < 10; i++) {
            SealDto sealDto = new SealDto();
            sealDto.setSealName("中国平安人寿保险股份有限公司保费业务专用章" + i);
            sealDto.setClassPath("C:\\Users\\wang\\Desktop\\seal\\" + i + ".pdf");
            sealDto.setValid(0);
            sealDto.setVersion(0);
            sealDtoList.add(sealDto);
        }

        Long startTime = System.currentTimeMillis();
        SealIndexFileUtil.writeSealIndex(sealDtoList);
        System.out.println("写入" + sealDtoList.size() + "条数据耗时：" + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        SealIndexFileUtil.writeSealIndexNew(sealDtoList);
        System.out.println("写入" + sealDtoList.size() + "条数据耗时New：" + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        List<SealDto> sealDtoList2 = SealIndexFileUtil.readSealIndex();
        System.out.println("读取 " + sealDtoList2.size() + "条数据耗时：" + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        List<SealDto> sealDtoList2New = SealIndexFileUtil.readSealIndexNew();
        System.out.println("读取 " + sealDtoList2New.size() + "条数据耗时New：" + (System.currentTimeMillis() - startTime) + "ms");

        SealDto sealDto = sealDtoList2.get(0);
        startTime = System.currentTimeMillis();
        writeSealIndex(sealDto);
        System.out.println("写入1条数据耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("写入1条数据后：" + (readSealIndex().size()));

        SealDto sealDtoNew = sealDtoList2New.get(0);
        startTime = System.currentTimeMillis();
        writeSealIndexNew(sealDtoNew);
        System.out.println("写入1条数据耗时New：" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("写入1条数据后New：" + (readSealIndexNew().size()));
    }

}
