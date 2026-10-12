using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenLookUpdate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject transformTarget;

		public FsmVector3 vectorTarget;

		public FsmFloat time;

		public iTweenFsmAction.AxisRestriction axis;

		private itDic hash;

		private GameObject go;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnExit()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoiTween()
		{
		}
	}
}
