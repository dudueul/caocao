namespace HutongGames.PlayMaker.Actions
{
	public class GetVector3XYZ : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		public FsmFloat storeX;

		public FsmFloat storeY;

		public FsmFloat storeZ;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetVector3XYZ()
		{
		}
	}
}
