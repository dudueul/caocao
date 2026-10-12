// name: ServerSetting$$ChangeServer
// entry: 015c6e94

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void ServerSetting__ChangeServer(undefined8 param_1,long *param_2)

{
  long *plVar1;
  undefined8 *puVar2;
  undefined8 uVar3;
  long lVar4;
  int *piVar5;
  ulong uVar6;
  
  if ((bRam00000000049f8bdb & 1) == 0) {
    func_0x010c65d4(0xbfe2);
    bRam00000000049f8bdb = 1;
  }
  if (param_2 != (long *)0x0) {
    if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar1 = (long *)ServerSetting__get_instance();
    if (plVar1 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar4 = *plVar1;
    if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
      uVar6 = 0;
      piVar5 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
      do {
        if (*(long *)(piVar5 + -2) == _class_IServerSetting_TypeInfo) {
          puVar2 = (undefined8 *)(lVar4 + (ulong)(*piVar5 + 5) * 0x10 + 0x130);
          goto code_r0x015c6f64;
        }
        uVar6 = uVar6 + 1;
        piVar5 = piVar5 + 4;
      } while (uVar6 < *(ushort *)(lVar4 + 0x122));
    }
    puVar2 = (undefined8 *)func_0x010bc584(plVar1,_class_IServerSetting_TypeInfo,5);
code_r0x015c6f64:
    uVar3 = (*(code *)*puVar2)(plVar1,puVar2[1]);
    lVar4 = *param_2;
    if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
      uVar6 = 0;
      piVar5 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
      do {
        if (*(long *)(piVar5 + -2) == _class_IServerSetting_TypeInfo) {
          puVar2 = (undefined8 *)(lVar4 + (ulong)(*piVar5 + 6) * 0x10 + 0x130);
          goto code_r0x015c6fcc;
        }
        uVar6 = uVar6 + 1;
        piVar5 = piVar5 + 4;
      } while (uVar6 < *(ushort *)(lVar4 + 0x122));
    }
    puVar2 = (undefined8 *)func_0x010bc584(param_2,_class_IServerSetting_TypeInfo,6);
code_r0x015c6fcc:
    (*(code *)*puVar2)(param_2,uVar3,puVar2[1]);
  }
  if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  **(long **)(_class_ServerSetting_TypeInfo + 0xb8) = (long)param_2;
  return;
}


