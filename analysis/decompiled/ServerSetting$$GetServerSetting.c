// name: ServerSetting$$GetServerSetting
// entry: 015d13c4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 ServerSetting__GetServerSetting(undefined8 param_1,int param_2,undefined4 param_3)

{
  undefined8 uVar1;
  
  if ((bRam00000000049f8bdd & 1) == 0) {
    func_0x010c65d4(0xbfe5);
    bRam00000000049f8bdd = 1;
  }
  if (99 < param_2) {
    if (param_2 == 100) {
      switch(param_3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar1 = func_0x010fdda8(_class_DevJPN_TypeInfo);
        DevJPN___ctor(uVar1,0);
        return uVar1;
      default:
        return 0;
      case 3:
        uVar1 = func_0x010fdda8(_class_StageJPN_TypeInfo);
        StageJPN___ctor(uVar1,0);
        return uVar1;
      case 4:
        uVar1 = func_0x010fdda8(_class_LiveJPN_TypeInfo);
        LiveJPN___ctor(uVar1,0);
        return uVar1;
      }
    }
    if (param_2 == 10000) {
      switch(param_3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar1 = func_0x010fdda8(_class_DevGLB_TypeInfo);
        DevGLB___ctor(uVar1,0);
        return uVar1;
      default:
        return 0;
      case 3:
        uVar1 = func_0x010fdda8(_class_StageGLB_TypeInfo);
        StageGLB___ctor(uVar1,0);
        return uVar1;
      case 4:
        uVar1 = func_0x010fdda8(_class_LiveGLB_TypeInfo);
        LiveGLB___ctor(uVar1,0);
        return uVar1;
      }
    }
    if (param_2 == 1000) {
      switch(param_3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar1 = func_0x010fdda8(_class_Dev_TypeInfo);
        Dev___ctor(uVar1,0);
        return uVar1;
      case 2:
        uVar1 = func_0x010fdda8(_class_DevAsLive_TypeInfo);
        DevAsLive___ctor(uVar1,0);
        return uVar1;
      case 3:
        uVar1 = func_0x010fdda8(_class_Stage_TypeInfo);
        Stage___ctor(uVar1,0);
        return uVar1;
      case 4:
        uVar1 = func_0x010fdda8(_class_Live_TypeInfo);
        Live___ctor(uVar1,0);
        return uVar1;
      default:
        return 0;
      }
    }
    return 0;
  }
  if (param_2 == 1) {
    uVar1 = 0;
    switch(param_3) {
    case 0:
code_r0x015d1568:
      uVar1 = func_0x010fdda8(_class_Local_TypeInfo);
      Local___ctor(uVar1,0);
      break;
    case 1:
      uVar1 = func_0x010fdda8(_class_DevTWN_TypeInfo);
      DevTWN___ctor(uVar1,0);
      break;
    case 3:
      uVar1 = func_0x010fdda8(_class_StageTWN_TypeInfo);
      StageTWN___ctor(uVar1,0);
      break;
    case 4:
      uVar1 = func_0x010fdda8(_class_LiveTWN_TypeInfo);
      LiveTWN___ctor(uVar1,0);
    }
  }
  else {
    if (param_2 != 10) {
      return 0;
    }
    uVar1 = 0;
    switch(param_3) {
    case 0:
      goto code_r0x015d1568;
    case 1:
      uVar1 = func_0x010fdda8(_class_DevCHN_TypeInfo);
      DevCHN___ctor(uVar1,0);
      break;
    case 3:
      uVar1 = func_0x010fdda8(_class_StageCHN_TypeInfo);
      StageCHN___ctor(uVar1,0);
      break;
    case 4:
      uVar1 = func_0x010fdda8(_class_LiveCHN_TypeInfo);
      LiveCHN___ctor(uVar1,0);
    }
  }
  return uVar1;
}


