namespace HutongGames.PlayMaker.Actions
{
	public class SetScale : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 vector;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

		public bool everyFrame;

		public bool lateUpdate;

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

		private void DoSetScale()
		{
		}
	}
}
