using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class iTweenScaleUpdate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject transformScale;

		public FsmVector3 vectorScale;

		public FsmFloat time;

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
