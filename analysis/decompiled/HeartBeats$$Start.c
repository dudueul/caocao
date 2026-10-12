// name: HeartBeats$$Start
// entry: 016cb0fc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__Start(long param_1)

{
  int iVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  long lVar7;
  long lVar8;
  
  if ((bRam00000000049f97a4 & 1) == 0) {
    func_0x010c65d4(0x6a25);
    bRam00000000049f97a4 = 1;
  }
  if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
  iVar1 = *(int *)(param_1 + 0x18);
  uVar4 = UnityEngine_Component__get_gameObject(param_1,0);
  uVar6 = _mm_Method_HeartBeats_SendHeartBeats__;
  uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar5,param_1,uVar6,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049fcec9 & 1) == 0) {
    func_0x010c65d4(0x2510,uVar4,uVar5,0xffffffff,0);
    bRam00000000049fcec9 = 1;
  }
  lVar3 = *(long *)(lVar3 + 0x20);
  uVar6 = func_0x010fdda8(_class_CentralTimer_TimerContext_TypeInfo);
  CentralTimer_TimerContext___ctor((float)iVar1,uVar6,uVar4,uVar5,0xffffffff);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  lVar7 = _mm_Method_System_Collections_Generic_List<CentralTimer_TimerContext>_Add__;
  iVar1 = *(int *)(lVar3 + 0x18);
  lVar8 = *(long *)(lVar3 + 0x10);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  if (iVar1 == *(int *)(lVar8 + 0x18)) {
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x88))(lVar3,1);
  }
  uVar2 = *(uint *)(lVar3 + 0x18);
  lVar7 = *(long *)(lVar3 + 0x10);
  *(uint *)(lVar3 + 0x18) = uVar2 + 1;
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  if (*(uint *)(lVar7 + 0x18) <= uVar2) {
    uVar4 = func_0x010ef850();
    func_0x010eebec(uVar4,0,0);
  }
  *(undefined8 *)(lVar7 + (long)(int)uVar2 * 8 + 0x20) = uVar6;
  *(int *)(lVar3 + 0x1c) = *(int *)(lVar3 + 0x1c) + 1;
  return;
}


