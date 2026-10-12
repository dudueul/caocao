using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenRotateUpdate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject transformRotation;

		public FsmVector3 vectorRotation;

		public FsmFloat time;

		public Space space;

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
