// name: ServerSetting$$get_S3_QA_URL
// entry: 015d1fe4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void ServerSetting__get_S3_QA_URL(void)

{
  undefined8 *puVar1;
  long lVar2;
  int *piVar3;
  ulong uVar4;
  long *plVar5;
  
  if ((bRam00000000049f8be6 & 1) == 0) {
    func_0x010c65d4(0xbfeb);
    bRam00000000049f8be6 = 1;
  }
  if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  plVar5 = (long *)**(undefined8 **)(_class_ServerSetting_TypeInfo + 0xb8);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar2 = *plVar5;
  if ((ulong)*(ushort *)(lVar2 + 0x122) != 0) {
    uVar4 = 0;
    piVar3 = (int *)(*(long *)(lVar2 + 0xb0) + 8);
    do {
      if (*(long *)(piVar3 + -2) == _class_IServerSetting_TypeInfo) {
        puVar1 = (undefined8 *)(lVar2 + (ulong)(*piVar3 + 8) * 0x10 + 0x130);
        goto code_r0x015d20ac;
      }
      uVar4 = uVar4 + 1;
      piVar3 = piVar3 + 4;
    } while (uVar4 < *(ushort *)(lVar2 + 0x122));
  }
  puVar1 = (undefined8 *)func_0x010bc584(plVar5,_class_IServerSetting_TypeInfo,8);
code_r0x015d20ac:
                    /* WARNING: Could not recover jumptable at 0x015d20bc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*(code *)*puVar1)(plVar5,puVar1[1]);
  return;
}


