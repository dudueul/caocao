namespace HutongGames.PlayMaker.Actions
{
	public class Vector3Add : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		[RequiredField]
		public FsmVector3 addVector;

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

		private void DoVector3Add()
		{
		}
	}
}
