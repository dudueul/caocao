namespace HutongGames.PlayMaker.Actions
{
	public class Vector3Multiply : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		[RequiredField]
		public FsmFloat multiplyBy;

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
	}
}
