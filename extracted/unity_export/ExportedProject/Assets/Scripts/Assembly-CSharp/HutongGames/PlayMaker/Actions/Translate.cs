using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Translate : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[Readonly]
		public FsmVector3 vector;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

		public Space space;

		public bool perSecond;

		public bool everyFrame;

		public bool lateUpdate;

		public bool fixedUpdate;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoTranslate()
		{
		}
	}
}
