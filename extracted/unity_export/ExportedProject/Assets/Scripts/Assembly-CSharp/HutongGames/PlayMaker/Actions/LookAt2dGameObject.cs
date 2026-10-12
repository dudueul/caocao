using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class LookAt2dGameObject : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject targetObject;

		public FsmFloat rotationOffset;

		public FsmBool debug;

		public FsmColor debugLineColor;

		public bool everyFrame;

		private GameObject go;

		private GameObject goTarget;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoLookAt()
		{
		}
	}
}
