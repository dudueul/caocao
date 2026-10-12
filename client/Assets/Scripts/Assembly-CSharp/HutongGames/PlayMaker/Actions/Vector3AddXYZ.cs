namespace HutongGames.PlayMaker.Actions
{
	public class Vector3AddXYZ : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		public FsmFloat addX;

		public FsmFloat addY;

		public FsmFloat addZ;

		public bool everyFrame;

		public bool perSecond;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoVector3AddXYZ()
		{
		}
	}
}
