package com.mmsofts.apachecommons.cmdChain;

/**
 * Created by mms on 5/30/16.
 */

/*
* Chain API models a computation as a series of "commands" that can be combined into a "chain".
* The API for a command consists of a single method (execute()), which is passed a "context"
* parameter containing the dynamic state of the computation, and whose return value is a boolean
* that determines whether or not processing for the current chain has been completed (true), or
* whether processing should be delegated to the next command in the chain (false).
*
Context. A Context represents the state of an application. In the Chain package, Context is a
marker interface for a java.util.Map. The Context is an envelope containing the attributes needed
to complete a transaction. In other words, a Context is a stateful object with member values.

Command. A Command represents a unit of work. A Command has a single entry method: public boolean
execute(Context context). A Command acts upon the state passed to it through a context object,
but retains no state of its own. Commands may be assembled into a Chain, so that a complex
transaction can be created from discrete units of work. If a Command returns true, then other
Commands in a Chain should not be executed. If a Command returns false, then other Commands in
the Chain (if any) may execute.

Chain.Chain implements the Command interface, so a Chain can be used interchangeably with a Command.
An application doesn't need to know if it's calling a Chain or a Command, so you can refactor from
one to the other. A Chain can nest other Chains as desired. This property is known as the Liskov
substitution principle.

Filter. Ideally, every command would be an island. In real life, we sometimes need to allocate
resources and be assured the resources will be released no matter what happens. A Filter is a
specialized Command that adds a postProcess method. A Chain is expected to call the postProcess
method of any filters in the chain before returning. A Command that implements Filter can safely
release any resources it allocated through the postProcess method, even if those resources are
shared with other Commands.

Catalog. Many applications use "facades" and "factories" and other techniques to avoid binding
layers too closely together. Layers need to interact, but often we don't want them to interact at
the classname level. A Catalog is a collection of logically named Commands (or Chains) that a client
 can execute, without knowing the Command's classname.
* */

public class CommandChainEx {




}
