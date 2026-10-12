namespace HutongGames.PlayMaker.Actions
{
	public class Vector3Subtract : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		[RequiredField]
		public FsmVector3 subtractVector;

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
